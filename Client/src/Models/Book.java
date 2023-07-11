package Models;

import java.io.Serializable;

public class Book implements Serializable {
    int id ;
    String ten_Sach;
    String mo_ta;
    String ngay_xuatban;
    int so_trang;
    String ibsn;
    String loai_sach;
    String nha_xuatban;
    public Book ()
    {
        super();
    }
    
    public Book(int id, String ten_Sach, String mo_ta, String ngay_xuatban, int so_trang, String ibsn, String loai_sach,
            String nha_xuatban) {
        this.id = id;
        this.ten_Sach = ten_Sach;
        this.mo_ta = mo_ta;
        this.ngay_xuatban = ngay_xuatban;
        this.so_trang = so_trang;
        this.ibsn = ibsn;
        this.loai_sach = loai_sach;
        this.nha_xuatban = nha_xuatban;
    }

    @Override
    public String toString() {
        return "Book [id=" + id + ", ten_Sach=" + ten_Sach + ", mo_ta=" + mo_ta + ", ngay_xuatban=" + ngay_xuatban
                + ", so_trang=" + so_trang + ", ibsn=" + ibsn + ", loai_sach=" + loai_sach + ", nha_xuatban="
                + nha_xuatban + "]";
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTen_Sach() {
        return ten_Sach;
    }
    public void setTen_Sach(String ten_Sach) {
        this.ten_Sach = ten_Sach;
    }
    public String getMo_ta() {
        return mo_ta;
    }
    public void setMo_ta(String mo_ta) {
        this.mo_ta = mo_ta;
    }
    public String getNgay_xuatban() {
        return ngay_xuatban;
    }
    public void setNgay_xuatban(String ngay_xuatban) {
        this.ngay_xuatban = ngay_xuatban;
    }
    public int getSo_trang() {
        return so_trang;
    }
    public void setSo_trang(int so_trang) {
        this.so_trang = so_trang;
    }
    public String getIbsn() {
        return ibsn;
    }
    public void setIbsn(String ibsn) {
        this.ibsn = ibsn;
    }
    public String getLoai_sach() {
        return loai_sach;
    }
    public void setLoai_sach(String loai_sach) {
        this.loai_sach = loai_sach;
    }
    public String getNha_xuatban() {
        return nha_xuatban;
    }
    public void setNha_xuatban(String nha_xuatban) {
        this.nha_xuatban = nha_xuatban;
    }
    
}
