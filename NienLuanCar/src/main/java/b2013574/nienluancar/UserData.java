package b2013574.nienluancar;

import java.sql.Date;

public class UserData {
    private Integer id;
    private String Username;
    private String Password;
    private String cn;
    private String email;
    private String user_id;
    private String hoten;
    private String gioitinh;
    private Date ngaysinh;
    private Date ngaythem;
    private Date ngaycapnhat;
    private Date ngayxoa;
    private String trangthai;
    public UserData(Integer id, String Username, String Password, String cn, String email, String user_id, String hoten,
                    String gioitinh, Date ngaysinh, Date ngaythem, Date ngaycapnhat, Date ngayxoa, String trangthai) {
        this.id = id;
        this.Username = Username;
        this.Password = Password;
        this.cn = cn;
        this.email = email;
        this.user_id = user_id;
        this.hoten = hoten;
        this.gioitinh = gioitinh;
        this.ngaysinh = ngaysinh;
        this.ngaythem = ngaythem;
        this.ngaycapnhat = ngaycapnhat;
        this.ngayxoa = ngayxoa;
        this.trangthai = trangthai;
    }

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return Username;
    }

    public String getPassword() {
        return Password;
    }

    public String getCn() {
        return cn;
    }

    public String getEmail() {
        return email;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getHoten() {
        return hoten;
    }
    public String getGioitinh() {
        return gioitinh;
    }

    public Date getNgaysinh() {
        return ngaysinh;
    }

    public Date getNgaythem() {
        return ngaythem;
    }

    public Date getNgaycapnhat() {
        return ngaycapnhat;
    }

    public Date getNgayxoa() {
        return ngayxoa;
    }

    public String getTrangthai() {
        return trangthai;
    }
}
