package com.example.common.ui;

import static com.example.common.constant.DataConstant.*;
import static com.example.common.constant.MessageConstant.*;
import static com.example.common.constant.RuleConstant.*;

import java.util.Scanner;

public abstract class BaseUi {

	public static final String ERROR_INPUT = "入力誤りです。再入力";
	public static final String ERROR_INPUT_INT = "数字を入れてください。再入力";
	public static final String ERROR_INPUT_STRING_MAX = "文字数は20までです。再入力";

	//「1 or 2 or e」を選ぶまで繰り返す。
	protected String selectOperationOnetoTwoInput(Scanner console, String message) {
		while (true) {
			String ans = stringInputBase(console, message);
			if (ans.equals(ONE) || ans.equals(TWO))
				return ans;
			else if (ans.equals(END))
				return ans;
			else
				System.out.println(ERROR_INPUT);
		}
	}

	//「1 or 2 or 3 or e」を選ぶまで繰り返す。
	protected String selectOperationOnetoThreeInput(Scanner console, String message) {
		while (true) {
			String ans = stringInputBase(console, message);
			if (ans.equals(ONE) || ans.equals(TWO) || ans.equals(THREE))
				return ans;
			else if (ans.equals(END))
				return ans;
			else
				System.out.println(ERROR_INPUT);
		}
	}

	//「1 or 2 or 3 or 4 or e」を選ぶまで繰り返す。
	protected String selectOperationOnetoFourInput(Scanner console) {
		while (true) {
			String ans = stringInputBase(console, COMMON_SELECT_OPERATION);
			if (ans.equals(ONE) || ans.equals(TWO)
					|| ans.equals(THREE) || ans.equals(FOUR))
				return ans;
			else if (ans.equals(END))
				return ans;
			else
				System.out.println(ERROR_INPUT);
		}
	}

	//戻り値が「続けるか終わる」だったら返す。それ以外は繰り返す
	protected String nextOrEndInput(Scanner console, String message) {
		String ans = null;
		while (true) {
			ans = stringInputBase(console, message);
			if (ans.equals(NEXT) || ans.equals(END))
				break;
			else
				System.out.println(ERROR_INPUT);
		}
		return ans;
	}

	protected boolean isNextOrEndInput(Scanner console, String message) {
		String ans = nextOrEndInput(console, message);
		if (ans.equals(END))
			return true;
		return false;
	}

	protected boolean isNextOrEndInput(Scanner console) {
		return isNextOrEndInput(console, COMMON_NEXT_OR_END);
	}

	//yかnを受け取り、yであればtrueをｎであればfalseを返す。それ以外はエラーメッセージを出し、再入力させる。
	protected boolean isYesNoInput(Scanner console, String message) {
		String ans = null;
		while (true) {
			ans = stringInputBase(console, message);
			if (ans.equals(YES) || ans.equals(NO))
				break;
			else
				System.out.println(ERROR_INPUT);
		}
		if (ans.equals(YES))
			return true;
		return false;
	}

	//コンソールから読み込んだ文字がバリデーションを守っているか
	protected String stringInput(Scanner console, String message) {
		String ans = null;
		while (true) {
			ans = stringInputBase(console, message);
			if (ans.length() <= MAX_STRING)
				break;
			else
				System.out.println(ERROR_INPUT_STRING_MAX);
		}
		return ans;
	}

	//商品名登録用処理
	protected String goodsNameInput(Scanner console) {
		return stringInput(console, GOODS_NAME);
	}

	//メッセージを表示して入力値を受け取り、文字列を返す。
	private String stringInputBase(Scanner console, String message) {
		String ans = null;
		while (true) {
			System.out.println(message);
			ans = console.next();
			if (ans == null)
				System.out.println(ERROR_INPUT);
			else
				break;
		}
		return ans;
	}

	//メッセージを表示して入力値を受け取り、整数値を返す。
	protected int intInput(Scanner console, String message) {
		int ans = 0;
		while (true) {
			System.out.println(message);
			try {
				ans = Integer.parseInt(console.next());
				break;
			} catch (NumberFormatException e) {
				System.out.println(ERROR_INPUT_INT);
			}
		}
		return ans;
	}

	//☆バリデーション判断メソッド↓*3

	//入力された数値が商品テーブルの登録用codeのバリデーションを守っているか判断し、正しい値が入力されるまで繰り返す。
	protected int goodsCodeInput(Scanner console) {
		int ans = 0;
		while (true) {
			ans = intInput(console, GOODS_CODE);
			if (ans >= MIN_GOODS_CODE && ans < MAX_GOODS_CODE)
				break;
			else
				System.out.println(ERROR_INPUT);
		}
		return ans;
	}

	//入力された数値が商品テーブルの登録用priceのバリデーションを守っているか判断し、正しい値が入力されるまで繰り返す。
	protected int priceInput(Scanner console) {
		int ans = 0;
		while (true) {
			ans = intInput(console, GOODS_PRICE);
			if (ans >= MIN_PRICE && ans < MAX_PRICE)
				break;
			else
				System.out.println(ERROR_INPUT);
		}
		return ans;
	}

	//入力された数値が商品テーブルの登録用quantityのバリデーションを守っているか判断し、正しい値が入力されるまで繰り返す。
	protected int quantityInput(Scanner console) {
		int ans = 0;
		while (true) {
			ans = intInput(console, GOODS_QUANTITY);
			if (ans >= MIN_QUANTITY && ans <= MAX_QUANTITY)
				break;
			else
				System.out.println(ERROR_INPUT);
		}
		return ans;
	}
}
