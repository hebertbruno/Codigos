

public class Parser {
	public static final int _EOF = 0;
	public static final int _numero = 1;
	public static final int _ident = 2;
	public static final int maxT = 40;

	static final boolean T = true;
	static final boolean x = false;
	static final int minErrDist = 2;

	public Token t;    // last recognized token
	public Token la;   // lookahead token
	int errDist = minErrDist;
	
	public Scanner scanner;
	public Errors errors;

	

	public Parser(Scanner scanner) {
		this.scanner = scanner;
		errors = new Errors();
	}

	void SynErr (int n) {
		if (errDist >= minErrDist) errors.SynErr(la.line, la.col, n);
		errDist = 0;
	}

	public void SemErr (String msg) {
		if (errDist >= minErrDist) errors.SemErr(t.line, t.col, msg);
		errDist = 0;
	}
	
	void Get () {
		for (;;) {
			t = la;
			la = scanner.Scan();
			if (la.kind <= maxT) {
				++errDist;
				break;
			}

			la = t;
		}
	}
	
	void Expect (int n) {
		if (la.kind==n) Get(); else { SynErr(n); }
	}
	
	boolean StartOf (int s) {
		return set[s][la.kind];
	}
	
	void ExpectWeak (int n, int follow) {
		if (la.kind == n) Get();
		else {
			SynErr(n);
			while (!StartOf(follow)) Get();
		}
	}
	
	boolean WeakSeparator (int n, int syFol, int repFol) {
		int kind = la.kind;
		if (kind == n) { Get(); return true; }
		else if (StartOf(repFol)) return false;
		else {
			SynErr(n);
			while (!(set[syFol][kind] || set[repFol][kind] || set[0][kind])) {
				Get();
				kind = la.kind;
			}
			return StartOf(syFol);
		}
	}
	
	void Simple3() {
		Expect(3);
		Expect(2);
		Expect(4);
		while (la.kind == 11 || la.kind == 12) {
			VarDecl();
		}
		while (la.kind == 6) {
			FunDecl();
		}
		Expect(5);
	}

	void VarDecl() {
		Tipo();
		Expect(2);
		while (la.kind == 9) {
			Get();
			Expect(2);
		}
		Expect(10);
	}

	void FunDecl() {
		Expect(6);
		Expect(2);
		Expect(7);
		Expect(8);
		Expect(4);
		while (StartOf(1)) {
			if (la.kind == 11 || la.kind == 12) {
				VarDecl();
			} else {
				Cmd();
			}
		}
		Expect(5);
	}

	void Cmd() {
		switch (la.kind) {
		case 2: {
			Get();
			if (la.kind == 13) {
				Get();
				Expr();
			} else if (la.kind == 7) {
				Get();
				Expect(8);
			} else if (la.kind == 14) {
				Get();
			} else if (la.kind == 15) {
				Get();
			} else SynErr(41);
			Expect(10);
			break;
		}
		case 16: {
			Get();
			Expect(2);
			Expect(17);
			Expect(1);
			Expect(18);
			Expect(1);
			Cmd();
			break;
		}
		case 19: {
			Get();
			Expect(7);
			Expr();
			Expect(8);
			Cmd();
			while (la.kind == 20) {
				Get();
				Expect(7);
				Expr();
				Expect(8);
				Cmd();
			}
			if (la.kind == 21) {
				Get();
				Cmd();
			}
			break;
		}
		case 22: {
			Get();
			Expect(7);
			Expr();
			Expect(8);
			Cmd();
			break;
		}
		case 23: {
			Get();
			Cmd();
			Expect(22);
			Expect(7);
			Expr();
			Expect(8);
			Expect(10);
			break;
		}
		case 24: {
			Get();
			Expect(2);
			Expect(10);
			break;
		}
		case 25: {
			Get();
			Expr();
			Expect(10);
			break;
		}
		case 26: {
			Get();
			Expect(10);
			break;
		}
		case 27: {
			Get();
			Expect(10);
			break;
		}
		case 14: {
			Get();
			Expect(2);
			Expect(10);
			break;
		}
		case 15: {
			Get();
			Expect(2);
			Expect(10);
			break;
		}
		case 4: {
			Get();
			while (StartOf(1)) {
				if (la.kind == 11 || la.kind == 12) {
					VarDecl();
				} else {
					Cmd();
				}
			}
			Expect(5);
			break;
		}
		default: SynErr(42); break;
		}
	}

	void Tipo() {
		if (la.kind == 11) {
			Get();
		} else if (la.kind == 12) {
			Get();
		} else SynErr(43);
	}

	void Expr() {
		AriExpr();
		if (StartOf(2)) {
			RelOp();
			AriExpr();
		}
	}

	void AriExpr() {
		Term();
		while (la.kind == 28 || la.kind == 29) {
			if (la.kind == 28) {
				Get();
			} else {
				Get();
			}
			Term();
		}
	}

	void RelOp() {
		if (la.kind == 35) {
			Get();
		} else if (la.kind == 36) {
			Get();
		} else if (la.kind == 37) {
			Get();
		} else if (la.kind == 38) {
			Get();
		} else if (la.kind == 39) {
			Get();
		} else SynErr(44);
	}

	void Term() {
		Fator();
		while (la.kind == 30 || la.kind == 31 || la.kind == 32) {
			if (la.kind == 30) {
				Get();
			} else if (la.kind == 31) {
				Get();
			} else {
				Get();
			}
			Fator();
		}
	}

	void Fator() {
		switch (la.kind) {
		case 2: {
			Get();
			break;
		}
		case 1: {
			Get();
			break;
		}
		case 33: {
			Get();
			break;
		}
		case 34: {
			Get();
			break;
		}
		case 29: {
			Get();
			Fator();
			break;
		}
		case 7: {
			Get();
			Expr();
			Expect(8);
			break;
		}
		default: SynErr(45); break;
		}
	}



	public void Parse() {
		la = new Token();
		la.val = "";		
		Get();
		Simple3();
		Expect(0);

	}

	private static final boolean[][] set = {
		{T,x,x,x, x,x,x,x, x,x,x,x, x,x,x,x, x,x,x,x, x,x,x,x, x,x,x,x, x,x,x,x, x,x,x,x, x,x,x,x, x,x},
		{x,x,T,x, T,x,x,x, x,x,x,T, T,x,T,T, T,x,x,T, x,x,T,T, T,T,T,T, x,x,x,x, x,x,x,x, x,x,x,x, x,x},
		{x,x,x,x, x,x,x,x, x,x,x,x, x,x,x,x, x,x,x,x, x,x,x,x, x,x,x,x, x,x,x,x, x,x,x,T, T,T,T,T, x,x}

	};
} // end Parser


class Errors {
	public int count = 0;                                    // number of errors detected
	public java.io.PrintStream errorStream = System.out;     // error messages go to this stream
	public String errMsgFormat = "-- line {0} col {1}: {2}"; // 0=line, 1=column, 2=text
	
	protected void printMsg(int line, int column, String msg) {
		StringBuffer b = new StringBuffer(errMsgFormat);
		int pos = b.indexOf("{0}");
		if (pos >= 0) { b.delete(pos, pos+3); b.insert(pos, line); }
		pos = b.indexOf("{1}");
		if (pos >= 0) { b.delete(pos, pos+3); b.insert(pos, column); }
		pos = b.indexOf("{2}");
		if (pos >= 0) b.replace(pos, pos+3, msg);
		errorStream.println(b.toString());
	}
	
	public void SynErr (int line, int col, int n) {
		String s;
		switch (n) {
			case 0: s = "EOF expected"; break;
			case 1: s = "numero expected"; break;
			case 2: s = "ident expected"; break;
			case 3: s = "\"program\" expected"; break;
			case 4: s = "\"{\" expected"; break;
			case 5: s = "\"}\" expected"; break;
			case 6: s = "\"void\" expected"; break;
			case 7: s = "\"(\" expected"; break;
			case 8: s = "\")\" expected"; break;
			case 9: s = "\",\" expected"; break;
			case 10: s = "\";\" expected"; break;
			case 11: s = "\"int\" expected"; break;
			case 12: s = "\"bool\" expected"; break;
			case 13: s = "\"=\" expected"; break;
			case 14: s = "\"++\" expected"; break;
			case 15: s = "\"--\" expected"; break;
			case 16: s = "\"for\" expected"; break;
			case 17: s = "\"in\" expected"; break;
			case 18: s = "\"..\" expected"; break;
			case 19: s = "\"if\" expected"; break;
			case 20: s = "\"elif\" expected"; break;
			case 21: s = "\"else\" expected"; break;
			case 22: s = "\"while\" expected"; break;
			case 23: s = "\"do\" expected"; break;
			case 24: s = "\"read\" expected"; break;
			case 25: s = "\"write\" expected"; break;
			case 26: s = "\"break\" expected"; break;
			case 27: s = "\"continue\" expected"; break;
			case 28: s = "\"+\" expected"; break;
			case 29: s = "\"-\" expected"; break;
			case 30: s = "\"*\" expected"; break;
			case 31: s = "\"/\" expected"; break;
			case 32: s = "\"%\" expected"; break;
			case 33: s = "\"true\" expected"; break;
			case 34: s = "\"false\" expected"; break;
			case 35: s = "\"==\" expected"; break;
			case 36: s = "\"<\" expected"; break;
			case 37: s = "\">\" expected"; break;
			case 38: s = "\"<=\" expected"; break;
			case 39: s = "\">=\" expected"; break;
			case 40: s = "??? expected"; break;
			case 41: s = "invalid Cmd"; break;
			case 42: s = "invalid Cmd"; break;
			case 43: s = "invalid Tipo"; break;
			case 44: s = "invalid RelOp"; break;
			case 45: s = "invalid Fator"; break;
			default: s = "error " + n; break;
		}
		printMsg(line, col, s);
		count++;
	}

	public void SemErr (int line, int col, String s) {	
		printMsg(line, col, s);
		count++;
	}
	
	public void SemErr (String s) {
		errorStream.println(s);
		count++;
	}
	
	public void Warning (int line, int col, String s) {	
		printMsg(line, col, s);
	}
	
	public void Warning (String s) {
		errorStream.println(s);
	}
} // Errors


class FatalError extends RuntimeException {
	public static final long serialVersionUID = 1L;
	public FatalError(String s) { super(s); }
}
