#include <iostream>
#include <cstring>
#include "classCard.hpp"
#include "classHand.hpp"

int main(int argc, char* argv[]) {

    // std::cout << argc << std::endl; // 自分自身も引数の1つとみなす

    if (argc == 3) {
        //std::cout << strlen(argv[1]) << std::endl;
        //std::cout << strlen(argv[2]) << std::endl;

        // 引数の文字列長を確認
        if (strlen(argv[1]) != 10 || strlen(argv[2]) != 10) {
            std::cerr << "Invalid argument" << std::endl;
            return (-1);
        }
        std::cout << argv[1] << std::endl;
        std::cout << argv[2] << std::endl;

        // カード一覧
        CCard cardA1, cardA2, cardA3, cardA4, cardA5;
        CCard cardB1, cardB2, cardB3, cardB4, cardB5;
        unsigned int cardcnt = 0;
        char rankarray[13] = {'2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K', 'A'};
        char suitarray[4] = {'c', 'd', 'h', 's'};

        //for(auto rank: {'2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K', 'A'}) {
        for(auto rank = 0; rank < 13; rank++) {
            //for(auto suit: {'c', 'd', 'h', 's'}) {
            for(auto suit = 0; suit < 4; suit++) {
                if (argv[1][0] == rankarray[rank] && argv[1][1] == suitarray[suit]){
                    const CCard tmp(rank, suit);
                    cardA1 = tmp;
                    cardcnt++;
                }
                if (argv[1][2] == rankarray[rank] && argv[1][3] == suitarray[suit]){
                    const CCard tmp(rank, suit);
                    cardA2 = tmp;
                    cardcnt++;
                }
                if (argv[1][4] == rankarray[rank] && argv[1][5] == suitarray[suit]){
                    const CCard tmp(rank, suit);
                    cardA3 = tmp;
                    cardcnt++;
                }
                if (argv[1][6] == rankarray[rank] && argv[1][7] == suitarray[suit]){
                    const CCard tmp(rank, suit);
                    cardA4 = tmp;
                    cardcnt++;
                }
                if (argv[1][8] == rankarray[rank] && argv[1][9] == suitarray[suit]){
                    const CCard tmp(rank, suit);
                    cardA5 = tmp;
                    cardcnt++;
                }
                if (argv[2][0] == rankarray[rank] && argv[2][1] == suitarray[suit]){
                    const CCard tmp(rank, suit);
                    cardB1 = tmp;
                    cardcnt++;
                }
                if (argv[2][2] == rankarray[rank] && argv[2][3] == suitarray[suit]){
                    const CCard tmp(rank, suit);
                    cardB2 = tmp;
                    cardcnt++;
                }
                if (argv[2][4] == rankarray[rank] && argv[2][5] == suitarray[suit]){
                    const CCard tmp(rank, suit);
                    cardB3 = tmp;
                    cardcnt++;
                }
                if (argv[2][6] == rankarray[rank] && argv[2][7] == suitarray[suit]){
                    const CCard tmp(rank, suit);
                    cardB4 = tmp;
                    cardcnt++;
                }
                if (argv[2][8] == rankarray[rank] && argv[2][9] == suitarray[suit]){
                    const CCard tmp(rank, suit);
                    cardB5 = tmp;
                    cardcnt++;
                }
            }
        }
        if (cardcnt == 10) { // 全てのカードがセットされた
            const CHand hand1({cardA1, cardA2, cardA3, cardA4, cardA5});
            const CHand hand2({cardB1, cardB2, cardB3, cardB4, cardB5});
            if (hand1 > hand2) {
                std::cout << "Former hand is higher" << std::endl;
                return 1;
            } else if (hand1 < hand2) {
                std::cout << "Latter hand is higher" << std::endl;
                return 2;
            } else {
                std::cout << "Same hand" << std::endl;
                return 3;
            }
        } else {
            std::cerr << "Card counter error" << std::endl;
            return -1;
        }
    } else {
        std::cout << "2 arguments must be specified" << std::endl;
        return -1;
    }

    return 0;
}
