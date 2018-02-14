package s4.b173358; // Please modify to s4.Bnnnnnn, where nnnnnn is your student ID.
import java.lang.*;
import s4.specification.*;

import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;
/*
interface FrequencerInterface {     // This interface provides the design for frequency counter.
    void setTarget(byte[]  target); // set the data to search.
    void setSpace(byte[]  space);  // set the data to be searched target from.
    int frequency(); //It return -1, when TARGET is not set or TARGET's length is zero
                    //Otherwise, it return 0, when SPACE is not set or Space's length is zero
                    //Otherwise, get the frequency of TAGET in SPACE
    int subByteFrequency(int start, int end);
    // get the frequency of subByte of taget, i.e target[start], taget[start+1], ... , target[end-1].
    // For the incorrect value of START or END, the behavior is undefined.
*/




public class Frequencer implements FrequencerInterface{
    // Code to start with: This code is not working, but good start point to work. byte [] myTarget;
    // Code with start：このコードは機能しませんが、良いスタートポイントがあります。 byte [] myTarget;
    byte [] mySpace;
    byte [] myTarget;
    boolean targetReady = false;
    boolean spaceReady = false;
    int [] suffixArray;



    // The variable, "suffixArray" is the sorted array of all suffixes of mySpace.
    // Each suffix is expressed by a interger, which is the starting position in mySpace.
    // The following is the code to print the variable
    //変数 "suffixArray"は、mySpaceのすべての接尾辞のソートされた配列です。
    //各接尾辞はintergerで表され、これはmySpaceの開始位置です。
    //以下は変数を出力するコードです

    private void printSuffixArray() {
        if(spaceReady) {
            for(int i=0; i< mySpace.length; i++) {
                int s = suffixArray[i];
                for(int j=s;j<mySpace.length;j++) {
                    System.out.write(mySpace[j]);
                }
                System.out.write('\n');
            }
        }
    }

    private int suffixCompare(int i, int j) {

        int si = suffixArray[i];
        int sj = suffixArray[j];
        int s = 0;
        if(si > s) s = si;
        if(sj > s) s = sj;
        int n = mySpace.length - s;
        for(int k=0;k<n;k++) {
            if(mySpace[si+k]>mySpace[sj+k]) return 1;
            if(mySpace[si+k]<mySpace[sj+k]) return -1;
        }
        if(si < sj) return 1;
        if(si > sj) return -1;

        return 0;
    }


/*
    public void quickSort(int leftIndex, int rightIndex)
	{
    	int[] array = suffixArray;
		// 左開始点と右開始点がぶつかるまで行う
		if(leftIndex <= rightIndex)
		{
			// 軸要素を中間点にとる
			int pivot = array[(leftIndex+rightIndex) / 2];
			int leftPos = leftIndex;
			int rightPos = rightIndex;

			//while(leftIndex <= rightIndex)
			while(leftIndex <= rightIndex)
			{
				// 左から軸要素より大きい値を探していく
				//while(array[leftIndex] < pivot)
				//while(suffixCompare(leftIndex, pivot) == -1)
				//int subarray1 = suffixCompare(leftIndex, pivot);
				while(suffixCompare(leftIndex, pivot) == 1)
				{
					leftIndex++;
					//System.out.println("left");
					//subarray1 = suffixCompare(leftIndex, pivot);
				}

				// 右から軸要素より小さな値を探していく
				//while(array[rightIndex] > pivot)
				//int subarray2 = suffixCompare(rightIndex, pivot);
				while(suffixCompare(rightIndex, pivot) == 1)
				{
					rightIndex--;
					//System.out.println("right");
					//subarray2 = suffixCompare(rightIndex, pivot);
				}

				// ぶつかったら交換
				if(leftIndex <= rightIndex)
				{
					byte tmp = mySpace[leftIndex];
					mySpace[leftIndex] = mySpace[rightIndex];
					mySpace[rightIndex] = tmp;

					//System.out.println("変換");

					// 交換したら探索続行
					leftIndex++;
					rightIndex--;
				}
			}

			// 軸要素より左側を再帰的にソート
			quickSort(leftIndex, rightPos);
			// 軸要素より右側を再帰的にソート
			quickSort(leftPos, rightIndex);
		}
	}
*/


    private void quickSort(int left, int right) {
    	int[] array = suffixArray;
    	if (left>=right) {
            return;
        }
		if (left <= right) {
			// 基準値
			int pivotData = (left + right) / 2;
			int leftPointer = left;
			int rightPointer = right;

			while (leftPointer <= rightPointer) {
				//if(leftPointer == rightPointer) break;
				// 左から基準値を超える要素を探す。（存在しない場合は基準値自体が対象となる）
				//while (array[leftPointer] < arry[pivotData]) {
				while (suffixCompare(leftPointer, pivotData) == -1) {
					leftPointer++;
					//System.out.println("1");
				}
				// 右から基準値を超える要素を探す。（存在しない場合は基準値自体が対象となる）
				//while (array[rightPointer] > pivotData) {
				while (suffixCompare(rightPointer, pivotData) == 1) {
					rightPointer--;
					//System.out.println("2");
				}

				//if (leftPointer <= rightPointer) {
				if (leftPointer <= rightPointer) {
					int tmp = suffixArray[leftPointer];
					suffixArray[leftPointer] = suffixArray[rightPointer];
					suffixArray[rightPointer] = tmp;
					if(leftPointer == pivotData) {
						pivotData = rightPointer;
					}
					if(rightPointer == pivotData) {
						pivotData = leftPointer;
					}

					leftPointer++;
					rightPointer--;
					//System.out.println("3");
				}
			}
			// 左半分、右半分を再帰的に呼び出す
			quickSort(left, rightPointer);
			quickSort(leftPointer, right);
		}
	}


    public void setSpace(byte []space) {
        mySpace = space;
        if(space.length>0) spaceReady = true;

        suffixArray = new int[space.length];

        for(int i = 0; i< space.length; i++) {
            suffixArray[i] = i;
        }
        quickSort(0, space.length-1);

/*
        //セレクションソート
		for (int i = 0; i < space.length; i++) {
			int min = i;
			for (int j = i + 1; j < space.length; j++){
				//if (array[min] > array[j]){
				if (suffixCompare(min, j) != -1){
					min = j;
				}
			}
			int tmp = suffixArray[i];
			suffixArray[i] = suffixArray[min];
			suffixArray[min] = tmp;
		}

         //バブルソート
	 int ans;
	int subsuffix;
        for(int i=0; i< space.length - 1 ; i++){
            for(int j=0; j < space.length - i - 1; j++){
                ans = suffixCompare(j, j+1);
                if(ans == 1){
                    subsuffix = suffixArray[j];
                    suffixArray[j] = suffixArray[j+1];
                    suffixArray[j+1] = subsuffix;
                }
            }
        }

*/


        //printSuffixArray();

    }

    private int targetCompare(int i, int start, int end) {
    // subBytesStartIndexとsubBytesEndIndexから呼び出されます。
        // "start"と "end"はsubByteStartIndexとsubByteEndIndexと同じです**
        // target_start_endはターゲットのサブバイト（開始、終了）です**
        // suffix_iとtarget_start_endを二次元順序で長さの制限付きで比較します。 ***

        // suffix_iの先頭がtarget_start_endに一致し、suffixがtargetより長い場合は0を返します。
        // もしsuffix_i > target_start_endならば1を返します。 **
        // suffix_i < target_start_endの場合-1を返します**

        //接尾辞の適切なインデックスを検索するために使用する必要があります。
        //検索の例

        for(int n = 0; n< (end - start); n++) {
            if(myTarget[start+n] > mySpace[suffixArray[i]+n]) { return -1; }
            if(myTarget[start+n] < mySpace[suffixArray[i]+n]) { return 1; }
        }
        //System.out.println(offset);

        return 0;
    }

    private int subByteStartIndex(int start, int end) {
	int spaceLength = mySpace.length;
	int startposition;
	int diff = spaceLength/10;
	for(int offset1 = 0; offset1< spaceLength; offset1 = offset1 + diff) {
		int dicide = targetCompare(offset1, start, end) ;
		if(dicide == 1) {
			for(int offset2 = offset1 - diff; offset2< spaceLength; offset2++) {
				startposition = targetCompare(offset2, start, end);
				if (startposition == 0) {
					return offset2;
				}
			}
		}
		if(dicide == 0) {
			return offset1;
		}
	}
	return 0;
    }

    private int subByteEndIndex(int start, int end) {
        int spaceLength = mySpace.length;
        int startposition;
        int diff = spaceLength/10;
        for(int offset1 = spaceLength-1; offset1 >= 0; offset1 = offset1 - diff) {
    		int dicide = targetCompare(offset1, start, end) ;
    		if(dicide == -1) {
    			for(int offset2 = offset1 + diff; offset2 >= 0; offset2--) {
    				startposition = targetCompare(offset2, start, end);
    				if (startposition == 0) {
    					return offset2+1;
    				}
    			}
    		}

    		if(dicide == 0) {
				return offset1+1;
			}
        }
	return 0;
    }


/*
    private int subByteStartIndex(int start, int end) {
    	int spaceLength = mySpace.length;
    	int startposition;
    	for(int offset = 0; offset< spaceLength; offset++) {
    	    startposition = targetCompare(offset, start, end);
    	    if (startposition == 0) {
    		return offset;
    	    }
    	}
    	return 0;
        }

        private int subByteEndIndex(int start, int end) {
            int spaceLength = mySpace.length;
            int startposition;
            for(int offset = spaceLength-1; offset >= 0; offset--) {
                startposition = targetCompare(offset, start, end);
                if (startposition == 0) {
                    return offset+1;
                }
            }
    	return 0;
        }
*/
    public int subByteFrequency(int start, int end) {
        int first = subByteStartIndex(start,end);
        int last1 = subByteEndIndex(start, end);
         //inspection code
         //検査コード
         //for(int k=start;k<end;k++) { System.out.write(myTarget[k]); }
         //System.out.printf(": first=%d last1=%d\n", first, last1);

        return last1 - first;
    }

    public void setTarget(byte [] target) {
        myTarget = target;
        if(myTarget.length>0) targetReady = true;
    }

    public int frequency() {
        if(targetReady == false) return -1;
        if(spaceReady == false) return 0;
        return subByteFrequency(0, myTarget.length);
    }

    public static void main(String[] args) {
        Frequencer frequencerObject;
        try {

            frequencerObject = new Frequencer();
            frequencerObject.setSpace("Hi Ho Hi Ho".getBytes());
            //frequencerObject.setSpace("                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        Hi Ho Hi Ho".getBytes());
            frequencerObject.setTarget("Ho".getBytes());
            int result = frequencerObject.frequency();
            System.out.print("Include = "+ result+" ");
            if(2 == result) { System.out.println("OK"); }
                else {System.out.println("WRONG"); }
        }
        catch(Exception e) {
            System.out.println("STOP");
        }
    }
}
