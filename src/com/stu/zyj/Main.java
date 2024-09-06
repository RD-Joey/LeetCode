package com.stu.zyj;

import java.util.*;

public class Main {
    final Random random = new Random();

    public static void main(String[] args) {
        String[][] input = new String[][] {{"EZE", "AXA"}, {"TIA", "ANU"}, {"ANU", "JFK"}, {"JFK", "ANU"}, {"ANU", "EZE"},
                {"TIA", "ANU"}, {"AXA", "TIA"}, {"TIA", "JFK"}, {"ANU", "TIA"}, {"JFK", "TIA"}};
        List<List<String>> tickets = new ArrayList<>(input.length);
        for (String[] str : input) {
            List<String> ticket = new ArrayList<>(2);
            ticket.add(str[0]);
            ticket.add(str[1]);
            tickets.add(ticket);
        }
        System.out.println(Arrays.toString(new Solution().kWeakestRows(new int[][]{{1, 1, 0, 0, 0}, {1, 1, 1, 1, 0}, {1, 0, 0, 0, 0}, {1, 1, 0, 0, 0}, {1, 1, 1, 1, 1}}, 3)));
    }
    public int candy(int[] ratings) {
        int[] cnts = new int[ratings.length];
        cnts[0] = 1;
        for (int i = 1; i < ratings.length; i++)
            cnts[i] = ratings[i] > ratings[i - 1] ? cnts[i - 1] + 1 : 1;
        int sum = cnts[ratings.length - 1];
        for (int i = ratings.length - 2; i >= 0; i--) {
            cnts[i] = Math.max(cnts[i], ratings[i] > ratings[i + 1] ? cnts[i + 1] + 1 : 1);
            sum += cnts[i];
        }
        return sum;
    }
    public List<List<Integer>> groupThePeople(int[] groupSizes) {
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < groupSizes.length; i++) {
            if (groupSizes[i] == 0) {
                continue;
            }
            List<Integer> list = new ArrayList<>();
            result.add(list);
            list.add(i);
            int size = groupSizes[i] - 1;
            for (int j = i + 1; j < groupSizes.length && size > 0; j++) {
                if (groupSizes[j] == groupSizes[i]) {
                    list.add(j);
                    groupSizes[j] = 0;
                    size--;
                }
            }
        }
        return result;
    }
    int[] ranks;
    int cars, minRank = 100, maxRank = 1;
    int[] rankCnts = new int[101];
    public long repairCars(int[] ranks, int cars) {
        this.ranks = ranks;
        this.cars = cars;
        for (int rank : ranks) {
            if (minRank > rank) minRank = rank;
            if (maxRank < rank) maxRank = rank;
            rankCnts[rank]++;
        }
        long r = (long) minRank * cars * cars, l = r / ranks.length / ranks.length, mid;
        while (l < r) {
            mid = (r + l) >>> 1;
            if (canRepair(mid)) {
                r = mid;
            } else l = mid + 1;
        }
        return r;
    }

    boolean canRepair(long time) {
        long carSum = 0;
        for (int i = minRank; i <= maxRank; i++) {
            if (rankCnts[i] == 0) continue;
            carSum += rankCnts[i] * (long) Math.sqrt((double) time / i);
            if (carSum >= cars) return true;
        }
        return false;
        /*int repairedCars = 0;
        for (int rank : ranks) {
            repairedCars += (int) Math.sqrt((double) time / rank);
            if (repairedCars >= cars) return true;
        }
        return false;*/
    }
    public int minTaps(int n, int[] ranges) {
        int[][] taps = new int[n + 1][2];
        for (int i = 0; i <= n; i++) {
            taps[i][0] = i - ranges[i];
            taps[i][1] = i + ranges[i];
        }
        Arrays.sort(taps, (a, b) -> a[0] - b[0]);
        int min = 0, right = 0, max = -1;
        for (int i = 0; i <= n && right < n; i++) {
            if (taps[i][0] > right) return -1;
            if (taps[i][0] == taps[i][1]) continue;
            while (i <= n && taps[i][0] <= right) {
                if (taps[i][1] > max) max = taps[i][1];
                i++;
            }
            right = max;
            min++;
            i--;
        }
        return min;
    }
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) return false;
        char[] toBeMatchedByS2 = new char[s2.length()], toBeMatchedByS1 = new char[s1.length()];
        char[] s1Chars = s1.toCharArray(), s2Chars = s2.toCharArray();
        int s1Index = 0, s2Index = 0, matchS1Index = 0, matchS2Index = 0;
        for (char ch : s3.toCharArray()) {
            if (s1Index < s1Chars.length && ch == s1Chars[s1Index]) {
                s1Index++;
            } else toBeMatchedByS2[matchS2Index++] = ch;
            if (s2Index < s2Chars.length && ch == s2Chars[s2Index]) {
                s2Index++;
            } else toBeMatchedByS1[matchS1Index++] = ch;
        }
        return s2.contentEquals(new String(toBeMatchedByS2))
                || s1.contentEquals(new String(toBeMatchedByS1));

    }
    public String reorganizeString(String s) {
        int[] cnt = new int[26];
        char[] chars = s.toCharArray();
        for (char aChar : chars) {
            cnt[aChar - 'a']++;
            //chars[i] = '\0';
        }
        int max = 0;
        for (int i = 1; i < 26; i++) {
            if (cnt[i] > cnt[max]) max = i;
        }
        if (chars.length < cnt[max] * 2 - 1) return "";
        if (cnt[max] == 1) return s;
        int gap = (chars.length + 1) / cnt[max];
        int ch = max, loop = 0, index = 0;
        while (loop < gap) {
            while (index < chars.length && cnt[ch] > 0) {
                chars[index] = (char) (ch + 'a');
                cnt[ch]--;
                index += gap;
            }
            if (cnt[ch] == 0)
                for (int i = 0; i < 26; i++)
                    if (cnt[i] > cnt[ch]) ch = i;
            if (index >= chars.length) index = ++loop;
        }
        return new String(chars);
    }
    public String convertToTitle(int columnNumber) {
        if (columnNumber == 1) return "A";
        int len = 0;
        for (int i = columnNumber; --i >= 0; i /= 26) {
            len++;
        }
        char[] chars = new char[len];
        for (int i = len - 1; i >= 0; i--) {
            chars[i] = (char) ('A' + (--columnNumber % 26));
            columnNumber /= 26;
        }
        return new String(chars);
    }
    public boolean repeatedSubstringPattern(String s) {
        for (int i = 1, half = s.length() / 2; i <= half; i++) {
            if (s.length() % i == 0) {
                StringBuilder str = new StringBuilder(s.substring(0, i));
                String pattern = new String(str);
                for (int j = 1, end = s.length() / i; j < end; j++) {
                    str.append(pattern);
                }
                System.out.println(str);
                System.out.println(s);
                if (s.contentEquals(str)) return true;
            }
        }
        return false;
    }
    public ListNode partition(ListNode head, int x) {
        ListNode sentinel = new ListNode(0, head), prev = sentinel, curr = head, insert = sentinel;
        while (curr != null) {
            if (curr.val < x) {
                if (insert.next != curr) {
                    prev.next = curr.next;
                    curr.next = insert.next;
                    insert.next = curr;
                } else prev = curr;
                insert = curr;
            } else prev = curr;
            curr = prev.next;
        }
        return sentinel.next;
    }
    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    public int findKthLargest(int[] nums, int k) {
        //return findKthLargestByHeap(nums, k);
        return findKthLargestByQuickSelect(nums, nums.length - k, 0, nums.length - 1);
    }
    public int findKthLargestByQuickSelect(int[] nums, int kIndex, int left, int right) {
        if (left == right) return nums[left];
        int pivot = random.nextInt(left, right + 1);
        if (pivot != left) {
            swap(nums, left, pivot);
            pivot = left;
        }
        for (int i = left; i <= right; i++) {
            if (nums[i] < nums[left])
                if (i != ++pivot) swap(nums, pivot, i);
        }
        if (left != pivot) swap(nums, left, pivot);
        if (pivot == kIndex) return nums[pivot];
        else return pivot < kIndex ? findKthLargestByQuickSelect(nums, kIndex,  pivot + 1, right) : findKthLargestByQuickSelect(nums, kIndex, left, pivot - 1);
    }
    public int findKthLargestByHeap(int[] nums, int k) {
        if (k == 1) {
            int res = nums[0];
            for (int i = 1; i < nums.length; i++) {
                if (nums[i] > res) res = nums[i];
            }
            return res;
        } else if (k == nums.length) {
            int res = nums[0];
            for (int i = 1; i < nums.length; i++) {
                if (nums[i] < res) res = nums[i];
            }
            return res;
        }
        for (int i = 1; i < k; i++) {
            int index = i, parent = (index - 1) / 2;
            while (index > 0) {
                if (nums[index] < nums[parent]) {
                    swap(nums, index, parent);
                    index = parent;
                    parent = (index - 1) / 2;
                } else break;
            }
        }
        for (int i = k; i < nums.length; i++) {
            if (nums[i] > nums[0]) {
                swap(nums, 0, k - 1);
                int index = 0, left = 1, right = 2;
                while (left < k - 1) {
                    if (right < k - 1 && nums[right] < nums[left] && nums[index] > nums[right]) {
                        swap(nums, index, right);
                        index = right;
                        left = index * 2 + 1;
                        right = left + 1;
                    } else if (nums[index] > nums[left]) {
                        swap(nums, index, left);
                        index = left;
                        left = index * 2 + 1;
                        right = left + 1;
                    } else break;
                }
                swap(nums, k - 1, i);
                index = k - 1;
                int parent = (index - 1) / 2;
                while (index > 0) {
                    if (nums[index] < nums[parent]) {
                        swap(nums, index, parent);
                        index = parent;
                        parent = (index - 1) / 2;
                    } else break;
                }
            }
        }
        return nums[0];
    }
    void swap(int[] nums, int i, int j) {
        nums[i] ^= nums[j];
        nums[j] ^= nums[i];
        nums[i] ^= nums[j];
    }
}
