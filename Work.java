interface Crypto{
	String encrypt(int [] posArr, char [] alp);
}
public class Work {
	public Crypto enc = (int [] pos, char [] cipherAlp) -> { 
		String s ="";
		for (int i: pos) {
			if (!(i>25)) {
				s+=cipherAlp[i];}
			else {s+=(char)i;}
		}
		return s;
	};
	public static char [] alpha() {
		char [] out = new char[26];
		for (char a='A',i=0;a<='Z';a++,i++) {
			out[i]=a;
		}
		return out;
	}
	public static char[] cipherAlp(String s) {
		char [] alp = alpha();
		int k = s.length();
		char [] out = new char[26];
		for (int i=0;i<s.length();i++) {
			out[i]=s.charAt(i);
			for (int j=0;j<alp.length;j++) {
				if (alp[j]==s.charAt(i)) {alp[j]='!';}
			}
		}
		for (int j=0;j<alp.length;j++) {
			if (alp[j]!='!') {
				out[k]=alp[j];
				k++;
			}	
		}
		return out;
	}
	public static int[] posArr(String s, char [] alp) {
		int [] out = new int [s.length()];
		for (int i = 0;i<s.length();i++) {
			if ((s.charAt(i)>'Z')||(s.charAt(i)<'A'))
				{out [i]=(int)s.charAt(i); continue;}
			for (int j = 0;j<alp.length;j++) {
				if (s.charAt(i)==alp[j]) {out[i]=j;}
			}
		}
		return out;
	}
	public static void main(String[] args) {
		char [] cipher = cipherAlp("TIGER");
		int[] posArr = posArr("THIS IS A STRING TO BE PROCEZZED!!",alpha());
		Work w = new Work();
		System.out.println(w.enc.encrypt(posArr, cipher));
		int [] dePosArr = posArr(w.enc.encrypt(posArr, cipher), cipher);
		System.out.println(w.enc.encrypt(dePosArr, alpha()));	
	}
}
