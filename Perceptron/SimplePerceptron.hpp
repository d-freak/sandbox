/**
 * SimplePerceptron.hpp
 * 
 * @Author
 *   Yuki Kawata
 */

#ifndef WIZ_ARTIFACT_SIMPLE_PERCEPTRON_H_
#define WIZ_ARTIFACT_SIMPLE_PERCEPTRON_H_

#include "Perceptron.hpp"

#include "Inaccuracy.hpp"
#include "VectorUtil.hpp"



namespace wiz {
    namespace artifact {

        /**
         * 単純パーセプトロン
         */
        class SimplePerceptron : public Perceptron {
        public:
            /**
             * コンストラクタ
             */
            explicit SimplePerceptron() {
            }

            /**
             * デストラクタ
             */
            virtual ~SimplePerceptron() {}

        public:
            /**
             * 識別関数
             * 
             * @param input 入力データ。
             * @param weight 重み。
             * @return 識別結果値。
             */
            virtual int predict(const std::vector<double>& input, const std::vector<double>& weight) const {
                return static_cast<int>(math::multiplies(weight, input));
            }

            /**
             * 学習関数
             * 
             * @param input 入力データ。
             * @param weight 重み。
             * @param factor 学習係数。
             */
            virtual std::vector<double> upgrade(const std::vector<double>& input, const std::vector<double>& weight, const int correct, const double factor = 0.1) {
                const auto& result = predict(input, weight);
                const auto& inaccuracy = getInaccuracy(result, correct);
                if (inaccuracy.getRate() == 0) {
                    // 誤差が無いので重みをそのまま採用
                    return weight;
                }
                else {
                    // 最終的な学習係数を算出
                    const auto& trimmedFactor = factor * inaccuracy.getCorrectValue();

                    // 重みベクトルに学習ベクトルを加算
                    //   upgrade_weight = weight + factor * input
                    const auto& learningData = math::multiplies_scalar(trimmedFactor, input);
                    return math::plus(weight, learningData);
                }
            }

        protected:
            /**
             * 誤差取得関数
             * 
             * @param target 誤差取得対象。
             * @param correct 誤差矯正パラメータ。
             * @return 誤差。
             */
            virtual Inaccuracy getInaccuracy(const int target, const int correct) const {
                if (target * correct > 0) {
                    // 正負が一致 -> 誤差無し
                    return Inaccuracy();
                }

                if (target < correct) {
                    // 値が小さすぎるから大きくなるように補正
                    return Inaccuracy(1.0, 1);
                }
                else {
                    // 値が大きすぎるから小さくなるように補正
                    return Inaccuracy(1.0, -1);
                }
            }

            /**
             * 伝播関数
             */
            virtual std::vector<double> propagation(const std::vector<double>& input) {
                // TODO 単純パーセプトロンには不要
                return input;
            }

        };

    }
}



#endif  // WIZ_ARTIFACT_SIMPLE_PERCEPTRON_H_
