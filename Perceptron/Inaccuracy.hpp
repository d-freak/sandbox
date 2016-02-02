/**
 * Inaccuracy.hpp
 * 
 * @Author
 *   Yuki Kawata
 */

#ifndef WIZ_ARTIFACT_INACCURACY_H_
#define WIZ_ARTIFACT_INACCURACY_H_



namespace wiz {
    namespace artifact {

        /**
         * 誤差
         */
        class Inaccuracy {
        public:
            /**
             * コンストラクタ
             */
            explicit Inaccuracy(const double rate = 0.0, const double correctValue = 0.0)
                    : _rate(rate), _correctValue(correctValue) {
            }

            /**
             * デストラクタ
             */
            ~Inaccuracy() {}

        public:
            /**
             * 矯正値を取得
             */
            double getCorrectValue() const {
                return _correctValue;
            }

            /**
             * 誤差の度合いを取得
             */
            double getRate() const {
                return _rate;
            }

        private:
            /**
             * 誤差の度合い
             */
            double _rate;

            /**
             * 矯正値
             */
            double _correctValue;

        };

    }
}



#endif  // WIZ_ARTIFACT_INACCURACY_H_
