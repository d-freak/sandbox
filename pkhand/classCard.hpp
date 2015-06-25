#ifndef __CARD_CLASS__
#define __CARD_CLASS__

/**
 * @class CCard classCard.hpp "inc/classCard.hpp"
 * @brief カード単体のクラス
 *
 * カード単体のクラス、ランクとスートを持つ
 * 代入や比較なども行える
 */
class CCard {
private:
	static const char rankarray[13];
	static const char suitarray[4];
	enum ERank {R2, R3, R4, R5, R6, R7, R8, R9, RT, RJ, RQ, RK, RA} ranke;
	enum ESuit {SC, SD, SH, SS} suite;
public:
	char suit;
	char rank;
	CCard(int ranki = 0, int suiti = 0); /* デフォルトコンストラクタ */

	CCard& operator =(const CCard& other); /* ランクとスートを上書き */
	bool operator ==(const CCard& other) const; /* ランクとスートを比較 */
	bool operator ==(const char RorS) const; /* ランクまたはスートを比較 */
	bool operator !=(const CCard& other) const; /* ランクとスートを比較 */
	bool operator >(const CCard& other) const; /* ランクのみ比較 */
	bool operator >=(const CCard& other) const; /* ランクのみ比較 */
	bool operator <(const CCard& other) const; /* ランクのみ比較 */
	bool operator <=(const CCard& other) const; /* ランクのみ比較 */
	bool isRankedWith(const CCard& other) const; /* ランクのみ比較 */
	bool isSuited(const CCard& other) const; /* スートのみ比較 */
	bool isConnected(const CCard& other) const; /* ランクのみ比較 */
};

#endif
