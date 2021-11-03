package base.simplebd;

import java.io.*;
import java.text.DecimalFormat;

public class Chave {
    protected int index;
    protected int type;
    protected String titulo;
    protected String autor;
    protected float preco;

    public Chave(int i,int q, String t, String a, float p) {
        this.index = i;
        this.type = q;
        this.titulo = t;
        this.autor = a;
        this.preco = p;
    }

    public Chave() {
        this.index = -1;
        this.type = -1;
        this.titulo = "";
        this.autor = "";
        this.preco = 0F;
    }

    public String toString() {
        DecimalFormat df = new DecimalFormat("#,##0.00");

        return "\nID....: " + this.index + "\nType...: " + this.type + "\nTítulo: " + this.titulo + "\nAutor.: " + this.autor + "\nPreço.: R$ "
                + df.format(this.preco);
    }

    public byte[] toByteArray() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        dos.writeInt(index);
        dos.writeInt(type);
        dos.writeUTF(titulo);
        dos.writeUTF(autor);
        dos.writeFloat(preco);
        return baos.toByteArray();
    }

    public void fromByteArray(byte[] ba) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(ba);
        DataInputStream dis = new DataInputStream(bais);
        index = dis.readInt();
        type = dis.readInt();
        titulo = dis.readUTF();
        autor = dis.readUTF();
        preco = dis.readFloat();
    }

}
