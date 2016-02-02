/**
 * PerceptronMain.cpp
 * 
 * @Author
 *   Yuki Kawata
 */

#include "SimplePerceptron.hpp"

#include <iostream>
#include <memory>
#include <unordered_map>
#include <utility>
#include <vector>



namespace {
    /**
     * ベクトルを生成
     */
    static inline std::vector<double> vec(const double x, const double y) {
        std::vector<double> result;
        result.push_back(x);
        result.push_back(y);
        return result;
    }
}



/**
 * エントリポイント
 */
int main() {
    std::unique_ptr<wiz::artifact::Perceptron> perceptron(new wiz::artifact::SimplePerceptron());

    // y = -x のグラフを推測できるか

    std::vector<std::pair<std::vector<double>, int>> sampleList;

    for (auto x = -1.0; x < 1.0; x += 0.01) {
        for (auto y = -1.0; y < 1.0; y += 0.01) {
            sampleList.push_back(std::make_pair(vec(x, y), ((y >= -x) ? 1 : -1)));
        }
    }

    // 初期値適当
    std::vector<double> weight;
    weight.push_back(40);
    weight.push_back(20);

    // 学習ループ
    const auto& learingCount = 1000U;
    for (auto i = 0U; i < learingCount; ++i) {
        for (auto it = std::begin(sampleList); it != std::end(sampleList); ++it) {
            weight = perceptron->upgrade(it->first, weight, it->second);
        }
    }

    std::cout << "重み：" << weight[0] << ", " << weight[1] << std::endl;

    std::cout << "座標 (0.5, -0.4) は y = -x より上の領域に";
    std::cout << ((perceptron->predict(vec(0.5, -0.4), weight) > 0) ? "ある" : "ない") << std::endl;
    std::cout << "  ⇒ あるよ" << std::endl;

    std::cout << "座標 (-2, 1.9) は y = -x より上の領域に";
    std::cout << ((perceptron->predict(vec(0.5, -0.4), weight) > 0) ? "ある" : "ない") << std::endl;
    std::cout << "  ⇒ ないよ" << std::endl;

    return 0;
}
