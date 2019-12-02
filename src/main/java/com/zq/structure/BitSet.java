package com.zq.structure;

/**
 * <p>Description: </p>
 * <p>Copyright: Copyright(c) 2019</p>
 * <p>Email: 1012872209@qq.com</p>
 *
 * @author zq
 * @date 2019-10-11:20:03
 */
public class BitSet {
    int[] bitset;

    public BitSet(int size) {
        bitset = new int[(size >> 5) + 1];
    }

    void set(int pos) {
        int wordNumber = (pos >> 5);
        int bitNumber = (pos & 0x1F);
        bitset[wordNumber] |= 1 << bitNumber;
    }

    boolean get(int pos) {
        int wordNumber = (pos >> 5);
        int bitNumber = (pos & 0x1F);
        return (bitset[wordNumber] & (1 << bitNumber)) == 1;
    }

}
