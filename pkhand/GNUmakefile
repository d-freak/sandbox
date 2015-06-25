# judgehand

judgehand: main.o classCard.o classHand.o
	g++ -pthread -o judgehand *.o

test: test.cpp
	g++ -std=c++0x -I./cpputest/include -L./cpputest/lib -lCppUTest -lCppUTestExt -o test test.cpp

main.o: main.cpp classCard.hpp classHand.hpp
	g++ -std=c++0x -I"./" -O0 -g3 -Wall -c -fmessage-length=0 -pthread -o "./main.o" "./main.cpp"

classCard.o: classCard.cpp classCard.hpp
	g++ -std=c++0x -I"./" -O0 -g3 -Wall -c -fmessage-length=0 -pthread -o "./classCard.o" "./classCard.cpp"

classHand.o: classHand.cpp classHand.hpp
	g++ -std=c++0x -I"./" -O0 -g3 -Wall -c -fmessage-length=0 -pthread -o "./classHand.o" "./classHand.cpp"

clean:
	rm judgehand *.o
