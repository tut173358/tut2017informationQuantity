package s4.b173358; // Please modify to s4.Bnnnnnn, where nnnnnn is your student ID.

import java.lang.*;
import s4.specification.*;

/*
interface FrequencerInterface {//このインタフェースは、周波数カウンタの設計を提供します。
    void setTarget（byte [] target）; //検索するデータを設定します。
    void setSpace（byte [] space）; //検索対象となるデータを設定します。
    int frequency（）; // TARGETが設定されていないか、TARGETの長さがゼロの場合は-1を返します
                    //それ以外の場合は、SPACEが設定されていないか、スペースの長さがゼロの場合は0を返します。
                    //それ以外の場合は、SPACEのTAGETの頻度を取得します。
    int subByteFrequency（int start、int end）;戻り値：
    // tagetのsubByteの頻度、つまりtarget [start]、taget [start + 1]、...、target [end-1]を取得します。
    // STARTまたはENDの値が正しくない場合の動作は未定義です。
}
*/

/*
パッケージs4.specification;
パブリックインターフェイスInformationEstimatorInterface {
    void setTarget（byte target []）; //情報量を計算するためのデータを設定する
    void setSpace（バイトスペース[]）; //サンプル空間のデータをコンピュータ確率に設定する
    二重推定（）; //ターゲットが設定されていないか、ターゲットの長さがゼロの場合は0.0を返します。
// true値が無限大の場合、または空白が設定されていない場合は、Double.MAX_VALUEを返します。
// true値がfineteでDouble.MAX_VALUEより大きい場合、動作は未定義です。
//これは、スペースが不当に大きい場合にのみ発生することに注意してください。とにかく他の問題に遭遇します。
//そうでなければ、情報量の推定、
}
*/

public class TestCase {
	public static void main(String[] args) {
		try {
			FrequencerInterface myObject;
			int freq;
			System.out.println("checking s4.b173358.Frequencer");
			myObject = new s4.b173358.Frequencer();
			myObject.setSpace("Hi Ho Hi Ho".getBytes());
			myObject.setTarget("H".getBytes());
			freq = myObject.frequency();
			System.out.print("\"H\" in \"Hi Ho Hi Ho\" appears " + freq + " times. ");
			if (4 == freq) {
				System.out.println("OK");
			} else {
				System.out.println("WRONG");
			}
		} catch (Exception e) {
			System.out.println("Exception occurred: STOP");
		}
		// TARGETの長さ0の確認
		try {
			FrequencerInterface myObject1;
			int freq1;
			System.out.println("checking s4.b173358.Frequencer");
			myObject1 = new s4.b173358.Frequencer();
			myObject1.setSpace("Hi Ho Hi Ho".getBytes());
			myObject1.setTarget("".getBytes());
			freq1 = myObject1.frequency();
			System.out.print("\" \" in \"Hi Ho Hi Ho\" appears " + freq1 + " times. ");
			if (-1 == freq1) {
				System.out.println("OK");
			} else {
				System.out.println("WRONG");
			}

		} catch (Exception e) {
			System.out.println("Exception occurred: STOP");
		}
		// SPACEの長さ0の確認
		try {
			FrequencerInterface myObject2;
			int freq2;
			byte[] a = new byte[0];
			System.out.println("checking s4.b173358.Frequencer");
			myObject2 = new s4.b173358.Frequencer();
			// myObject2.setSpace("".getBytes());
			myObject2.setSpace(a);
			myObject2.setTarget("H".getBytes());
			freq2 = myObject2.frequency();
			System.out.print("\"H\" in \" \" appears " + freq2 + " times. ");
			if (0 == freq2) {
				System.out.println("OK");
			} else {
				System.out.println("WRONG");
			}
		} catch (Exception e) {
			System.out.println("Exception occurred: STOP");
		}
		// TARGETが未設定か確認
		try {
			FrequencerInterface myObject;
			int freq;
			System.out.println("checking s4.b173358.Frequencer");
			myObject = new s4.b173358.Frequencer();
			myObject.setSpace("Hi Ho Hi Ho".getBytes());
			// myObject.setTarget("H".getBytes());
			freq = myObject.frequency();
			System.out.print("\"H\" in \"Hi Ho Hi Ho\" appears " + freq + " times. ");
			if (-1 == freq) {
				System.out.println("OK");
			} else {
				System.out.println("WRONG");
			}
		} catch (Exception e) {
			System.out.println("Exception occurred: STOP");
		}
		// SPACEが未設定か確認
		try {
			FrequencerInterface myObject;
			int freq;
			System.out.println("checking s4.b173358.Frequencer");
			myObject = new s4.b173358.Frequencer();
			// myObject.setSpace("Hi Ho Hi Ho".getBytes());
			myObject.setTarget("H".getBytes());
			freq = myObject.frequency();
			System.out.print("\"H\" in \"Hi Ho Hi Ho\" appears " + freq + " times. ");
			if (0 == freq) {
				System.out.println("OK");
			} else {
				System.out.println("WRONG");
			}
		} catch (Exception e) {
			System.out.println("Exception occurred: STOP");
		}
        
        
        
        
        
		try {
			InformationEstimatorInterface myObject;
			double value;
			System.out.println("checking s4.b173355.InformationEstimator");
			myObject = new s4.b173355.InformationEstimator();
			myObject.setSpace("3210321001230123".getBytes());
			myObject.setTarget("0".getBytes());
			value = myObject.estimation();
			System.out.println(">0 " + value);
			myObject.setTarget("01".getBytes());
			value = myObject.estimation();
			System.out.println(">01 " + value);
			myObject.setTarget("0123".getBytes());
			value = myObject.estimation();
			System.out.println(">0123 " + value);
			myObject.setTarget("00".getBytes());
			value = myObject.estimation();
			System.out.println(">00 " + value);
		} catch (Exception e) {
			System.out.println("Exception occurred: STOP");
		}
		// TARGETが未設定か確認
		try {
			InformationEstimatorInterface myObject1;
			double value1;
			System.out.println("checking s4.b173358.InformationEstimator");
			myObject1 = new s4.b173358.InformationEstimator();
			myObject1.setSpace("3210321001230123".getBytes());
			// myObject1.setTarget("0".getBytes());
			value1 = myObject1.estimation();
			System.out.println(">0 " + value1);
			value1 = myObject1.estimation();
			System.out.println(">01 " + value1);
			value1 = myObject1.estimation();
			System.out.println(">0123 " + value1);
			value1 = myObject1.estimation();
			System.out.println(">00 " + value1);
			if (0.0 == value1) {
				System.out.println("OK");
			} else {
				System.out.println("WRONG");
			}
		} catch (Exception e) {
			System.out.println("Exception occurred: STOP");
		}
		// TARGETの 長さ0
		try {
			InformationEstimatorInterface myObject1;
			double value1;
			System.out.println("checking s4.b173358.InformationEstimator");
			myObject1 = new s4.b173358.InformationEstimator();
			myObject1.setSpace("3210321001230123".getBytes());
			myObject1.setTarget("".getBytes()); // TARGETの 長さ0
			value1 = myObject1.estimation();
			System.out.println(">0 " + value1);
			value1 = myObject1.estimation();
			System.out.println(">01 " + value1);
			value1 = myObject1.estimation();
			System.out.println(">0123 " + value1);
			value1 = myObject1.estimation();
			System.out.println(">00 " + value1);
			if (0.0 == value1) {
				System.out.println("OK");
			} else {
				System.out.println("WRONG");
			}
		} catch (Exception e) {
			System.out.println("Exception occurred: STOP");
		}
		// SPACEの長さ0の確認
		try {
			InformationEstimatorInterface myObject1;
			double value1;
			System.out.println("checking s4.b173358.InformationEstimator");
			myObject1 = new s4.b173358.InformationEstimator();
			myObject1.setSpace("".getBytes());// SPACEの長さ0
			myObject1.setTarget("0".getBytes());
			value1 = myObject1.estimation();
			System.out.println(">0 " + value1);
			value1 = myObject1.estimation();
			System.out.println(">01 " + value1);
			value1 = myObject1.estimation();
			System.out.println(">0123 " + value1);
			value1 = myObject1.estimation();
			System.out.println(">00 " + value1);
			if (Double.MAX_VALUE == value1) {
				System.out.println("OK");
			} else {
				System.out.println("WRONG");
			}
		} catch (Exception e) {
			System.out.println("Exception occurred: STOP");
		}
        //SPACE未定義
		try {
			InformationEstimatorInterface myObject1;
			double value1;
			System.out.println("checking s4.b173358.InformationEstimator");
			myObject1 = new s4.b173358.InformationEstimator();
			// myObject1.setSpace("".getBytes());//SPACE未定義
			myObject1.setTarget("0".getBytes());
			value1 = myObject1.estimation();
			System.out.println(">0 " + value1);
			value1 = myObject1.estimation();
			System.out.println(">01 " + value1);
			value1 = myObject1.estimation();
			System.out.println(">0123 " + value1);
			value1 = myObject1.estimation();
			System.out.println(">00 " + value1);
			if (Double.MAX_VALUE == value1) {
				System.out.println("OK");
			} else {
				System.out.println("WRONG");
			}
		} catch (Exception e) {
			System.out.println("Exception occurred: STOP");
		}
	}
}
