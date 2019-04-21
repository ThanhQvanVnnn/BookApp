package com.phungthanhquan.bookapp.Model;

import com.phungthanhquan.bookapp.ConnectAPI.BuildAPI;
import com.phungthanhquan.bookapp.ConnectAPI.CaNhanMethodAPI;
import com.phungthanhquan.bookapp.ConnectAPI.DanhMucMethodAPI;
import com.phungthanhquan.bookapp.ConnectAPI.TrangChuMethodAPI;
import com.phungthanhquan.bookapp.ConnectAPI.TuSachMethodAPI;

public class DatabaseAPI {
    private static final String BASE_URL = "";
    public static TrangChuMethodAPI getApiServiceTrangChu(){
        return BuildAPI.getClient(BASE_URL).create(TrangChuMethodAPI.class);
    }
    public static DanhMucMethodAPI getApiServiceDanhMuc(){
        return BuildAPI.getClient(BASE_URL).create(DanhMucMethodAPI.class);
    }
    public static TuSachMethodAPI getApiServiceTuSach(){
        return BuildAPI.getClient(BASE_URL).create(TuSachMethodAPI.class);
    }
    public static CaNhanMethodAPI getApiServiceCaNhan(){
        return BuildAPI.getClient(BASE_URL).create(CaNhanMethodAPI.class);
    }
}
