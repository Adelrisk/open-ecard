package org.openecard.mdlw.sal.cryptoki;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import java.util.Arrays;
import java.util.List;
/**
 * <i>native declaration : pkcs11_v2.40/pkcs11t.h</i><br>
 * This file was autogenerated by <a href="http://jnaerator.googlecode.com/">JNAerator</a>,<br>
 * a tool written by <a href="http://ochafik.com/">Olivier Chafik</a> that <a href="http://code.google.com/p/jnaerator/wiki/CreditsAndLicense">uses a few opensource projects.</a>.<br>
 * For help, please visit <a href="http://nativelibs4java.googlecode.com/">NativeLibs4Java</a> , <a href="http://rococoa.dev.java.net/">Rococoa</a>, or <a href="http://jna.dev.java.net/">JNA</a>.
 */
public class CK_GCM_PARAMS extends Structure {
	/** C type : CK_BYTE_PTR */
	public Pointer pIv;
	public Pointer getPIv() {
		return pIv;
	}
	public void setPIv(Pointer pIv) {
		this.pIv = pIv;
	}
	/** C type : CK_ULONG */
	public long ulIvLen;
	public long getUlIvLen() {
		return ulIvLen;
	}
	public void setUlIvLen(long ulIvLen) {
		this.ulIvLen = ulIvLen;
	}
	/** C type : CK_ULONG */
	public long ulIvBits;
	public long getUlIvBits() {
		return ulIvBits;
	}
	public void setUlIvBits(long ulIvBits) {
		this.ulIvBits = ulIvBits;
	}
	/** C type : CK_BYTE_PTR */
	public Pointer pAAD;
	public Pointer getPAAD() {
		return pAAD;
	}
	public void setPAAD(Pointer pAAD) {
		this.pAAD = pAAD;
	}
	/** C type : CK_ULONG */
	public long ulAADLen;
	public long getUlAADLen() {
		return ulAADLen;
	}
	public void setUlAADLen(long ulAADLen) {
		this.ulAADLen = ulAADLen;
	}
	/** C type : CK_ULONG */
	public long ulTagBits;
	public long getUlTagBits() {
		return ulTagBits;
	}
	public void setUlTagBits(long ulTagBits) {
		this.ulTagBits = ulTagBits;
	}
	public CK_GCM_PARAMS() {
		super();
	}
	 protected List<String> getFieldOrder() {
		return Arrays.asList("pIv", "ulIvLen", "ulIvBits", "pAAD", "ulAADLen", "ulTagBits");
	}
	/**
	 * @param pIv C type : CK_BYTE_PTR<br>
	 * @param ulIvLen C type : CK_ULONG<br>
	 * @param ulIvBits C type : CK_ULONG<br>
	 * @param pAAD C type : CK_BYTE_PTR<br>
	 * @param ulAADLen C type : CK_ULONG<br>
	 * @param ulTagBits C type : CK_ULONG
	 */
	public CK_GCM_PARAMS(Pointer pIv, long ulIvLen, long ulIvBits, Pointer pAAD, long ulAADLen, long ulTagBits) {
		super();
		this.pIv = pIv;
		this.ulIvLen = ulIvLen;
		this.ulIvBits = ulIvBits;
		this.pAAD = pAAD;
		this.ulAADLen = ulAADLen;
		this.ulTagBits = ulTagBits;
	}
	public CK_GCM_PARAMS(Pointer peer) {
		super(peer);
	}
	public static class ByReference extends CK_GCM_PARAMS implements Structure.ByReference {
		
	};
	public static class ByValue extends CK_GCM_PARAMS implements Structure.ByValue {
		
	};
}
