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
        // comparing two suffixes by dictionary order.
        // i and j denoetes suffix_i, and suffix_j
        // 辞書順で2つの接尾辞を比較する。
        // iとjはsuffix_iとsuffix_jをデノートします
        
        // if suffix_i > suffix_j, it returns 1
        // if suffix_i < suffix_j, it returns -1
        // if suffix_i = suffix_j, it returns 0;
        
        
        
        // It is not implemented yet,
        // It should be used to create suffix array.
        // Example of dictionary order
        //まだ実装されていませんが、
        //接尾辞配列の作成に使用する必要があります。
        //辞書順の例
        
        // "i"  < "o"       : compare by code // コードで比較する
        // "Hi" < "Ho"      ; if head is same, compare the next element // headが同じなら、次の要素を比較する
        // "Ho" < "Ho "     ; if the prefix is identical, longer string is big // 接頭辞が同一であれば、長い文字列が大きい
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
 
    public void setSpace(byte []space) {
        int ans;
        int subsuffix;
        mySpace = space;
        if(mySpace.length>0) spaceReady = true;
        suffixArray = new int[space.length];
        // put all suffixes in suffixArray. Each suffix is expressed by one interger.
        //すべての接尾辞を接尾辞Arrayに入れます。 各接尾辞は整数で表されます。
        for(int i = 0; i< space.length; i++) {
            suffixArray[i] = i;
        }
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
        
        

        /*
	try{
	String result = new String(space, "UTF-8");
	Map<Integer, String> map = new HashMap<Integer, String>();
	
	for(int i = 0; i< space.length; i++) {
	    map.put(i, result.substring(i)); //Hashmapに追加
	}
	
	 Object[] mapkey = map.keySet().toArray();
         Arrays.sort(mapkey); //Hashmapをソート

	 for (Integer nKey : map.keySet())
        {
            System.out.println(map.get(nKey));
        }
    */
	 /*
        for(int i = 0; i< space.length; i++) {
            suffixArray[i] = map.get(i).;
	    System.out.print(suffixArray[i]);
        }
	 */
        // Sorting is not implmented yet.
        //ソートはまだ実装されていません。
        /* Example from "Hi Ho Hi Ho"
        0: Hi Ho
        1: Ho
        2: Ho Hi Ho 
        3:Hi Ho
        4:Hi Ho Hi Ho 
        5:Ho
        6:Ho Hi Ho 
        7:i Ho
        8:i Ho Hi Ho 
        9:o
        A:o Hi Ho
        */
        //
        printSuffixArray();
            /*
} catch (ArithmeticException e) {
            System.out.println("例外が発生しました。");
            System.out.println(e);
 
            return 0;
        }
             */
        
    }
    
    private int targetCompare(int i, int start, int end) {
    // It is called from subBytesStarIndex, and subBytesEndIndex.
    // "start" and "end" are same as in subByteStartIndex and subByteEndIndex **
    // target_start_end is subBytes(start, end) of target **
    // comparing suffix_i and target_start_end by dictonary order with limitation of length; ***
    // if the beginning of suffix_i matches target_start_end, and suffix is longer than target it returns 0;
    // if suffix_i > target_start_end it return 1; **
    // if suffix_i < target_start_end it return -1 **
    // It should be used to search the apropriate index of some suffix.
    // Example of search

    // subBytesStartIndexとsubBytesEndIndexから呼び出されます。
        // "start"と "end"はsubByteStartIndexとsubByteEndIndexと同じです**
        // target_start_endはターゲットのサブバイト（開始、終了）です**
        // suffix_iとtarget_start_endを二次元順序で長さの制限付きで比較します。 ***

        // suffix_iの先頭がtarget_start_endに一致し、suffixがtargetより長い場合は0を返します。
        // もしsuffix_i > target_start_endならば1を返します。 **
        // suffix_i < target_start_endの場合-1を返します**
        
        //接尾辞の適切なインデックスを検索するために使用する必要があります。
        //検索の例





    // 変更前
    // suffix_iとtarget_j_endを二次元順序で長さの制限付きで比較します。
	// suffix_iの先頭がtarget_j_endと一致し、suffixがtargetより長い場合は0を返します。
	// もしsuffix_i> target_j_endならば1を返します。
	// suffix_i <target_j_endなら-1を返します
	// まだ実装されていません。
	//接尾辞の適切なインデックスを検索するために使用する必要があります。
	// 検索の例



	/*
	if (suffix[i] == target[i][end] && suffix.length > target.length){return 0;}
	if (suffix[i] > target[i][end]) {return 1;}
	if (suffix[i] < target[i][end]) {return -1;}
	*/
        
        // suffix       target
        // "o"      >   "i"
        // "o"      <   "z"
        // "o"      =   "o"
        // "o"      <   "oo"
        // "Ho"     >   "Hi"
        // "Ho"     <   "Hz"
        // "Ho"     =   "Ho"
        // "Ho"     <   "Ho" :"Ho"is not in the head of suffix "Ho" // "Ho"は接尾辞 "Ho"の頭にない
        // "Ho"     =   "H" : "H" is in the head of suffix "Ho" // "H"は接尾辞 "Ho"の頭にあります
        
        for(int n = 0; n< (end - start); n++) {
            if(myTarget[start+n] > mySpace[suffixArray[i]+n]) { return -1; }
            if(myTarget[start+n] < mySpace[suffixArray[i]+n]) { return 1; }
        }
        //System.out.println(offset);
        
        return 0;
    }
    
    private int subByteStartIndex(int start, int end) {
        // It returns the index of the first suffix which is equal or greater than subBytes;
        // not implemented yet;
        // For "Ho", it will return 5 for "Hi Ho Hi Ho".
        // For "Ho ", it will return 6 for "Hi Ho Hi Ho".
         // subBytes以上の最初の接尾辞のインデックスを返します。
	// まだ実装されていません。
	// "Ho"の場合、 "Hi Ho Hi Ho"の場合は5を返します。
	// "Ho "の場合、 "Hi Ho Hi Ho"は6を返します。

	
	int spaceLength = mySpace.length;
    int startposition;
	for(int offset = 0; offset< spaceLength; offset++) {
//            boolean abort = false;
//            for(int i = 0; i< (end - start); i++) {
//                if(myTarget[start+i] != mySpace[suffixArray[offset]+i]) { abort = true; break; }
//            }
//            if(abort == false) {  System.out.println(offset); return (offset);}
        startposition = targetCompare(offset, start, end);
        if (startposition == 0) {
            //System.out.println("start:" + offset);
            return offset;
        }
  }
	
	
	

	/*
	for(int offset = 0; offset< spaceLength - (end - start); offset++) {
	    boolean abort = false;
            for(int i = 0; i< (end - start); i++) {
                if(myTarget[start+i] != suffixArray[offset+i]) { abort = true; break; } 
            }
            if(abort == false) {  System.out.println(offset+1+end - start); return (offset+1+end - start);} 

	}
	*/
	return 0;

	
        
    }
    
    private int subByteEndIndex(int start, int end) {
        // It returns the next index of the first suffix which is greater than subBytes;
        // not implemented yet
        // For "Ho", it will return 7 for "Hi Ho Hi Ho".
        // For "Ho ", it will return 7 for "Hi Ho Hi Ho".
         // subBytesより大きい最初の接尾辞の次のインデックスを返します。
         // まだ実装されていません
	// "Ho"の場合、 "Hi Ho Hi Ho"の場合は7を返します。
	// "Ho "の場合、 "Hi Ho Hi Ho"の場合は7を返します。
        
        int spaceLength = mySpace.length;
        int startposition;
        for(int offset = spaceLength-1; offset >= 0; offset--) {
            //            boolean abort = false;
            //            for(int i = 0; i< (end - start); i++) {
            //                if(myTarget[start+i] != mySpace[suffixArray[offset]+i]) { abort = true; break; }
            //            }
            //            if(abort == false) {  System.out.println(offset); return (offset);}
            startposition = targetCompare(offset, start, end);
            if (startposition == 0) {
                //System.out.println("end:" + (offset+1));
                return offset+1;
            }
        }

        
        

	/*
	boolean flag = false;
	int spaceLength = mySpace.length;
	for(int offset = 0; offset< spaceLength - (end - start); offset++) {
            boolean abort = false;
            for(int i = 0; i< (end - start); i++) {
                if(myTarget[start+i] != mySpace[offset+i]) {
		    if (flag == true){
			System.out.println(offset+end - start);
			return (offset+end - start);
		    }
		    abort = true;
		    break;
		} 
            }
            if(abort == false) { flag = true;} 
        }
	*/
	return 0;
    }
    
    public int subByteFrequency(int start, int end) {
        /* This method could be defined as follows though it is slow.
         //このメソッドは遅いですが、以下のように定義することができます。
         int spaceLength = mySpace.length;
         int count = 0;
         for(int offset = 0; offset< spaceLength - (end - start); offset++) {
            boolean abort = false;
            for(int i = 0; i< (end - start); i++) {
                if(myTarget[start+i] != mySpace[offset+i]) { abort = true; break; } 
            }
            if(abort == false) { count++; } 
         }
        */
        int first = subByteStartIndex(start,end);
        int last1 = subByteEndIndex(start, end);
         //inspection code
         //検査コード
         for(int k=start;k<end;k++) { System.out.write(myTarget[k]); } 
         System.out.printf(": first=%d last1=%d\n", first, last1);
        
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
