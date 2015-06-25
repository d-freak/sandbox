#include <iostream>
#include <algorithm>
#include "classHand.hpp"

/* CHand */

/* private */
bool CHand::isF() { /* フラッシュかどうかの判定 */
	for(auto i = 0u; i < (cards.size()-1); ++i) {
		if(!(cards.at(i).isSuited(cards.at(i+1))))
			return false; /* 1つでも異なるスートがあればFalse */
	}
	return true;
}

bool CHand::isS() { /* ストレートかどうかの判定 */
	for(auto i = 0u; i < (cards.size()-1); ++i) {
		if(!(cards.at(i).isConnected(cards.at(i+1))))
			return false; /* 1つでもコネクタ以外があればFalse */
	}
	return true;
}

void CHand::swap(CCard& c1, CCard& c2) {
	CCard tmp = c1;
	c1 = c2;
	c2 = tmp;
}

void CHand::setHand(std::array<CCard, 5> crds) {
	/* 全く同じカードがないかチェック */
	for(auto i =0u; i<5; ++i) {
		for(auto j =i+1; j<5; ++j) {
			if(crds.at(i) == crds.at(j)) {
				/* 同じカードが紛れ込んでいたら最弱ハンドで上書き */
				std::cerr << "Error hand found.(completely same cards)" << std::endl;
				handtype = NONE;
				cards.at(0) = CCard(0, 0); /* 2c */
				cards.at(1) = CCard(1, 0); /* 3c */
				cards.at(2) = CCard(2, 0); /* 4c */
				cards.at(3) = CCard(3, 0); /* 5c */
				cards.at(4) = CCard(5, 1); /* 7d */
				return;
			}
		}
	}

	/* カードをランクについて昇順にセット */
	cards = crds;
	std::sort(cards.begin(), cards.end()); /* 5枚のカードをソート */
	if((cards.at(0) == '2') && (cards.at(1) == '3') && (cards.at(2) == '4') &&
		(cards.at(3) == '5') && (cards.at(4) == 'A')) {
		/* A-5のストレートなら並び替え */
		CCard tmp = cards.at(4);
		cards.at(4) = cards.at(3);
		cards.at(3) = cards.at(2);
		cards.at(2) = cards.at(1);
		cards.at(1) = cards.at(0);
		cards.at(0) = tmp;
	}

	/**
	 * 役判定を行うと同時にカードを並び替えて同じ役の強さ比較に備える
	 * 評価に使うカードは優先すべきものほど後ろに置くこと
	 */
	bool flagF = isF();
	bool flagS = isS();
	if(flagF) {
		if(flagS) {
			handtype = SF;
		} else {
			handtype = F;
		}
	} else if(flagS) {
		handtype = S;
	} else if (cards.at(0).isRankedWith(cards.at(1))) { /* MMXXX */
		if (cards.at(1).isRankedWith(cards.at(2))) { /* MMMXX */
			if (cards.at(2).isRankedWith(cards.at(3))) { /* MMMMS */
				handtype = FK;
				swap(cards.at(0), cards.at(4)); /* MMMMS->SMMMM */
			} else if (cards.at(3).isRankedWith(cards.at(4))) { /* MMMSS */
				handtype = FH;
				swap(cards.at(0), cards.at(3)); /* MMMSS->SMMSM */
				swap(cards.at(1), cards.at(4)); /* SMMSM->SSMMM */
			} else { /* MMMBA */
				handtype = TK;
				swap(cards.at(0), cards.at(3)); /* MMMBA->BMMMA */
				swap(cards.at(1), cards.at(4)); /* BMMMA->BAMMM */
			}
		} else if (cards.at(2).isRankedWith(cards.at(3))) { /* XXMMY */
			if (cards.at(3).isRankedWith(cards.at(4))) { /* AAMMM */
				handtype = FH;
			} else { /* AAMMB */
				handtype = TP;
				swap(cards.at(2), cards.at(4)); /* AAMMB->AABMM */
				swap(cards.at(0), cards.at(2)); /* AABMM->BAAMM */
			}
		} else if (cards.at(3).isRankedWith(cards.at(4))) { /* AABMM */
			handtype = TP;
			swap(cards.at(0), cards.at(2)); /* AABMM->BAAMM */
		} else { /* MMCBA */
			handtype = OP;
			swap(cards.at(0), cards.at(4)); /* MMCBA->AMCBM */
			swap(cards.at(1), cards.at(3)); /* AMCBM->ABCMM */
			swap(cards.at(0), cards.at(2)); /* ABCMM->CBAMM */
		}
	} else if (cards.at(1).isRankedWith(cards.at(2))) { /* YMMXX */
		if (cards.at(2).isRankedWith(cards.at(3))) {  /* YMMMX */
			if (cards.at(3).isRankedWith(cards.at(4))) { /* AMMMM */
				handtype = FK;
			} else { /* BMMMA */
				handtype = TK;
				swap(cards.at(1), cards.at(4)); /* BMMMA->BAMMM */
			}
		} else if (cards.at(3).isRankedWith(cards.at(4))) {  /* BAAMM */
			handtype = TP;
		} else { /* CMMBA */
			handtype = OP;
			swap(cards.at(1), cards.at(3)); /* CMMBA->CBMMA */
			swap(cards.at(2), cards.at(4)); /* CBMMA->CBAMM */
		}
	} else if (cards.at(2).isRankedWith(cards.at(3))) { /* ZYMMX */
		if (cards.at(3).isRankedWith(cards.at(4))) { /* BAMMM */
			handtype = TK;
		} else { /* CBMMA */
			handtype = OP;
			swap(cards.at(2), cards.at(4)); /* CBMMA->CBAMM */
		}
	} else if (cards.at(3).isRankedWith(cards.at(4))) { /* CBAMM */
		handtype = OP;
	} else { /* EDBCA */
		handtype = NONE;
	}
}

/* public */

/**
 * デフォルトコンストラクタでは最弱ハンドをセットする
 */
CHand::CHand() {
	/* 初期値として弱いハンドをセット */
	handtype = NONE;
	cards.at(0) = CCard(0, 0); /* 2c */
	cards.at(1) = CCard(1, 0); /* 3c */
	cards.at(2) = CCard(2, 0); /* 4c */
	cards.at(3) = CCard(3, 0); /* 5c */
	cards.at(4) = CCard(5, 1); /* 7d */
}

/**
 * インスタンス生成時に引数がセットされたら役判定を行う
 */
CHand::CHand(std::array<CCard, 5> crds) {
	setHand(crds);
	//judgeHand();
	// debug
	for(auto card:cards) {
		std::cout << card.rank << card.suit;
	}
	std::cout << "(type:" << int(handtype) << ")" << std::endl;
	// debug
}

/* otherは既に役判定済みなので再判定はしない */
CHand& CHand::operator =(const CHand& other) {
	handtype = other.handtype;
	cards = other.cards;
	return *this;
}

bool CHand::operator ==(const CHand& other) const {
	if(handtype == other.handtype &&
		cards.at(0).isRankedWith(other.cards.at(0)) &&
		cards.at(1).isRankedWith(other.cards.at(1)) &&
		cards.at(2).isRankedWith(other.cards.at(2)) &&
		cards.at(3).isRankedWith(other.cards.at(3)) &&
		cards.at(4).isRankedWith(other.cards.at(4))) {
		return true;
	} else {
		return false;
	}
}

bool CHand::operator >(const CHand& other) const {
	if(handtype > other.handtype) {
		return true;
	} else if(handtype == other.handtype) {
		/* 最後のカードから比較する */
		if(cards.at(4) > other.cards.at(4)) {
			return true;
		}
		if(cards.at(3) > other.cards.at(3)) {
			return true;
		}
		if(cards.at(2) > other.cards.at(2)) {
			return true;
		}
		if(cards.at(1) > other.cards.at(1)) {
			return true;
		}
		if(cards.at(0) > other.cards.at(0)) {
			return true;
		}
	}
	/* 比較対象より強い点が1つも無ければfalseを返す */
	return false;
}

bool CHand::operator <(const CHand& other) const {
	if(handtype < other.handtype) {
		return true;
	} else if(handtype == other.handtype) {
		/* 最後のカードから比較する */
		if(cards.at(4) < other.cards.at(4)) {
			return true;
		}
		if(cards.at(3) < other.cards.at(3)) {
			return true;
		}
		if(cards.at(2) < other.cards.at(2)) {
			return true;
		}
		if(cards.at(1) < other.cards.at(1)) {
			return true;
		}
		if(cards.at(0) < other.cards.at(0)) {
			return true;
		}
	}
	/* 比較対象より弱い点が1つも無ければfalseを返す */
	return false;
}
