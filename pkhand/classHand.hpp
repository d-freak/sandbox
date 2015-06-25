#ifndef __HAND_CLASS__
#define __HAND_CLASS__
#include <array>
#include "classCard.hpp"

/**
 * @class CHand classHand.hpp "inc/classHand.hpp"
 * @brief 5枚のカードからなる役
 *
 * 解説は未作成
 */
class CHand {
public:
	enum EHandType {NONE, OP, TP, TK, S, F, FH, FK, SF} handtype;
	std::array<CCard, 5> cards;
private:
	bool isF();
	bool isS();
	void swap(CCard& c1, CCard& c2);
	void setHand(std::array<CCard, 5> crds);
public:
	CHand(); /* デフォルトコンストラクタ */
	CHand(std::array<CCard, 5> crds); /* デフォルトコンストラクタ（引数あり） */

	CHand& operator =(const CHand& other); /* ハンドを上書き */
	bool operator ==(const CHand& other) const; /* ハンドの強さを比較 */
	bool operator >(const CHand& other) const; /* ハンドの強さを比較 */
	bool operator <(const CHand& other) const; /* ハンドの強さを比較 */
};

#endif
