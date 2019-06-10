package encryption;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Classe responsável por criptografar dados. 
 * @author Isaías de França Leite.
 */
public class AES {
    static String IV = "AAAAAAAAAAAAAAAA";
    public static String textopuro = "";
    public static String chaveencriptacao = "";

    /**
     * Função de criptografar dados.
     * @param textopuro - texto original.
     * @param chaveencriptacao - chave da criptografia.
     * @return - retorna o texto 
     * @throws Exception
     */
    public static byte[] encrypt(String textopuro, String chaveencriptacao) throws Exception {
        Cipher encripta = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
        SecretKeySpec key = new SecretKeySpec(chaveencriptacao.getBytes("UTF-8"), "AES");
        encripta.init(Cipher.ENCRYPT_MODE, key,new IvParameterSpec(IV.getBytes("UTF-8")));
        return encripta.doFinal(textopuro.getBytes("UTF-8"));
    }
 
    /**
     * Função de descriptografar dados.
     * @param textoencriptado - texto original. 
     * @param chaveencriptacao - chave da criptografia.
     * @return - retorna o texto descriptografado.
     * @throws Exception
     */
    public static String decrypt(byte[] textoencriptado, String chaveencriptacao) throws Exception{
        Cipher decripta = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
        SecretKeySpec key = new SecretKeySpec(chaveencriptacao.getBytes("UTF-8"), "AES");
        decripta.init(Cipher.DECRYPT_MODE, key,new IvParameterSpec(IV.getBytes("UTF-8")));
        return new String(decripta.doFinal(textoencriptado),"UTF-8");
     }
}