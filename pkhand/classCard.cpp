#include "classCard.hpp"

/* CCard */
const char CCard::rankarray[13] = {'2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K', 'A'};
const char CCard::suitarray[4] = {'c', 'd', 'h', 's'};

//CCard::CCard(int ranki, int suiti): Rank(*this) {
CCard::CCard(int ranki, int suiti) {
	ranke = ERank(ranki);
	rank = rankarray[ranki];
	suite = ESuit(suiti);
	suit = suitarray[suite];
}

/**
 * ランクとスートを上書き
 */
CCard& CCard::operator =(const CCard& other) {
	ranke = other.ranke; suite = other.suite;
	rank = other.rank; suit = other.suit;
	return *this;
}

/**
 * ランクとスートを両方共比較
 */
bool CCard::operator ==(const CCard& other) const {
	return ((rank == other.rank) && (suit == other.suit)) ? true : false;
}

/**
 * ランクまたはスートを両方共比較
 */
bool CCard::operator ==(const char RorS) const {
	return ((rank == RorS) || (suit == RorS)) ? true : false;
}

/**
 * ランクとスートを両方共比較
 */
bool CCard::operator !=(const CCard& other) const {
	return ((rank != other.rank) || (suit != other.suit)) ? true : false;
}

/**
 * ランクのみを比較
 */
bool CCard::operator >(const CCard& other) const {
	return (ranke > other.ranke) ? true : false;
}

/**
 * ランクのみを比較
 */
bool CCard::operator >=(const CCard& other) const {
	return (ranke >= other.ranke) ? true : false;
}

/**
 * ランクのみを比較
 */
bool CCard::operator <(const CCard& other) const {
	return (ranke < other.ranke) ? true : false;
}

/**
 * ランクのみを比較
 */
bool CCard::operator <=(const CCard& other) const {
	return (ranke <= other.ranke) ? true : false;
}

/**
 * ランクのみを比較
 */
bool CCard::isRankedWith(const CCard& other) const {
	return (ranke == other.ranke) ? true : false;
}

/**
 * スートのみを比較
 */
bool CCard::isSuited(const CCard& other) const {
	return (suit == other.suit) ? true : false;
}

/**
 * ランクのみを比較
 */
bool CCard::isConnected(const CCard& other) const {
	return ((int(ranke) + 1 == int(other.ranke)) ||
			 (int(ranke) - 1 == int(other.ranke)) ||
			 (int(ranke) + 12 == int(other.ranke)) || /* Aと2のケース */
			 (int(ranke) - 12 == int(other.ranke))) ? true : false;
}
