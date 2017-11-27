package linearHash;

import java.util.Scanner;

public class linearHash {
	private Entry[] entries = new Entry[11];
	private int size = 0;
	private int used = 0;
	private double loadFactor = 0.75;
	// NIL entry
	private final Entry NIL = new Entry(null, null);

	class Entry {
		Object key, value;

		Entry(Object k, Object v) {
			this.key = k;
			this.value = v;
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

	}

	public Object get(Object key) {
		int h = hash(key);
		for (int i = 0; i < entries.length; i++) {
			int j = nextProbe(h, i);
			Entry entry = entries[j];
			if (entry == null)
				// No have key
				break;
			if (entry == NIL)
				// Searching next table
				continue;
			if (entry.key.equals(key))
				// Success : find key
				return entry.value;
		}
		// Fail : Don't find key
		return null;
	}

	public Object put(Object key, Object value) {
		if (used > loadFactor * entries.length)
			// if loadFactor > 0.75
			rehash();
		// Hasing key
		int h = hash(key);
		for (int i = 0; i < entries.length; i++) {
			int j = nextProbe(h, i);
			Entry entry = entries[j];
			if (entry == null) {
				// Nobody occupied table
				entries[j] = new Entry(key, value);
				++size;
				++used;
				return null;
			}
			if (entry == NIL) {
				continue;
			}
			if (entry.key.equals(key)) {
				Object oldValue = entry.value;
				// add 1 - if have same value
				entries[j].value = (int) entries[j].value + 1;
				// Success update
				return oldValue;
			}
		}
		return null; // fail : Table overflow
	}

	public Object remove(Object key) {
		// Hasing key
		int h = hash(key);
		for (int i = 0; i < entries.length; i++) {
			int j = nextProbe(h, i);
			Entry entry = entries[j];
			if (entry == null)
				break;
			if (entry == NIL)
				continue;
			if (entries[j].key.equals(key)) {
				// Success : remove entry
				Object oldValue = entries[j].value;
				entries[j] = NIL;
				--size;
				return oldValue;
			}
		}
		// Fail
		return null;
	}

	/* size getter */
	public int size() {
		return size;
	}

	private int hash(Object key) {
		if (key == null)
			throw new IllegalArgumentException();
		return (key.hashCode() & 0x7FFFFFFF) % entries.length;
	}

	private void rehash() {
		Entry[] oldEntries = entries;
		entries = new Entry[2 * oldEntries.length + 1];
		for (int k = 0; k < oldEntries.length; k++) {
			Entry entry = oldEntries[k];
			if (entry == null || entry == NIL)
				// NIL references are not copied
				continue;
			int h = hash(entry.key);// 달라진 entries.length로 키를 다시 구함
			for (int i = 0; i < entries.length; i++) {
				int j = nextProbe(h, i);// overflow 처리
				if (entries[j] == null) {
					entries[j] = entry;
					break;
				}
			}
		}
		used = size;
	}

	private int nextProbe(int h, int i) {
		return (h + i) % entries.length;// LinearProbing
	}
}