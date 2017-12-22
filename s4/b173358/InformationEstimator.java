package s4.b173358; // Please modify to s4.Bnnnnnn, where nnnnnn is your student ID.

import java.lang.*;
import s4.specification.*;

/*s4.specificationからインポートされるもの
パッケージs4.specification;
パブリックインターフェイスInformationEstimatorInterface {
     void setTarget（byte target []）; //情報量を計算するためのデータを設定する
     void setSpace（バイトスペース[]）; //サンプル空間のデータをコンピュータ確率に設定する
     double estimation（）; //ターゲットが設定されていないか、ターゲットの長さがゼロの場合は0.0を返します。
// true値が無限大の場合、または空白が設定されていない場合は、Double.MAX_VALUEを返します。
// true値がfineteでDouble.MAX_VALUEより大きい場合の動作は未定義です。
//これは、スペースが不当に大きい場合にのみ発生することに注意してください。 とにかく他の問題に遭遇します。
//そうでなければ、情報量の推定、
}
*/

public class InformationEstimator implements InformationEstimatorInterface {
	// Code to tet, *warning: This code condtains intentional problem*
	byte[] myTarget; // data to compute its information quantity
	byte[] mySpace; // Sample space to compute the probability
	FrequencerInterface myFrequencer; // Object for counting frequency

	byte[] subBytes(byte[] x, int start, int end) {
		// corresponding to substring of String for byte[] ,
		// It is not implement in class library because internal structure of byte[]
		// requires copy.
		byte[] result = new byte[end - start];
		for (int i = 0; i < end - start; i++) {
			result[i] = x[start + i];
		}
		;
		return result;
	}

	// IQ: information quantity for a count, -log2(count/sizeof(space))
	double iq(int freq) {
		return -Math.log10((double) freq / (double) mySpace.length) / Math.log10((double) 2.0);
	}

	public void setTarget(byte[] target) {
		myTarget = target;
	}

	public void setSpace(byte[] space) {
		myFrequencer = new Frequencer();
		mySpace = space;
		myFrequencer.setSpace(space);
	}

	public double estimation() {

		// System.out.println("np="+np+" length="+myTarget.length);
		double value = Double.MAX_VALUE; // value = mininimum of each "value1".

		if (myTarget == null) {
			value = 0;
		} else if (myTarget.length == 0) {
			value = 0;
		} else if (mySpace == null) {
			return value;
		} else if (mySpace.length == 0) {
			return value;
		} else {
			boolean[] partition = new boolean[myTarget.length + 1];
			int np;
			np = 1 << (myTarget.length - 1);
			for (int p = 0; p < np; p++) { // There are 2^(n-1) kinds of partitions.
				// binary representation of p forms partition.
				// for partition {"ab" "cde" "fg"}
				// a b c d e f g : myTarget
				// T F T F F T F T : partition:
				partition[0] = true; // I know that this is not needed, but..
				for (int i = 0; i < myTarget.length - 1; i++) {
					partition[i + 1] = (0 != ((1 << i) & p));
				}
				partition[myTarget.length] = true;

				// Compute Information Quantity for the partition, in "value1"
				// value1 = IQ(#"ab")+IQ(#"cde")+IQ(#"fg") for the above example
				double value1 = (double) 0.0;
				int end = 0;
				;
				int start = end;
				while (start < myTarget.length) {
					// System.out.write(myTarget[end]);
					end++;
					;
					while (partition[end] == false) {
						// System.out.write(myTarget[end]);
						end++;
					}
					// System.out.print("("+start+","+end+")");
					myFrequencer.setTarget(subBytes(myTarget, start, end));
					value1 = value1 + iq(myFrequencer.frequency());
					start = end;
				}
				// System.out.println(" "+ value1);

				// Get the minimal value in "value"
				if (value1 < value)
					value = value1;
			}
		}
		return value;
	}

	public static void main(String[] args) {
		InformationEstimator myObject;
		double value;
		myObject = new InformationEstimator();
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
	}
}
