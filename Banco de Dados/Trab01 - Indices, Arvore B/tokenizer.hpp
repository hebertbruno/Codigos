#ifndef _LIB_TOKENIZER_HPP_
#define _LIB_TOKENIZER_HPP_

#include <string>
#include <iostream>
#include <fstream>
#include <vector>
#include <boost/regex.hpp>
#include <boost/algorithm/string/regex.hpp>


class Tokenizer
{
public:
	Tokenizer(){

	};
	~Tokenizer(){

	};
	bool verify_number(const char string[]);
	int parser(const char arquivo[], const Hash& hash);
	void fix_string(std::string& str);

};

#endif
