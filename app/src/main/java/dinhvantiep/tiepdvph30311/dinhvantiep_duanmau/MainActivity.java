package dinhvantiep.tiepdvph30311.dinhvantiep_duanmau;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private View view;
    private TextView txtuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout=findViewById(R.id.id_drawer);
        toolbar=findViewById(R.id.toolbar1);
        txtuser=findViewById(R.id.txtUser);

        setSupportActionBar(toolbar);
        ActionBar bar=getSupportActionBar();

        bar.setHomeAsUpIndicator(R.drawable.sach);
        bar.setDisplayHomeAsUpEnabled(true);

        NavigationView nav=findViewById(R.id.navi);
        view=nav.getHeaderView(0);
        txtuser=view.findViewById(R.id.txtUser);
        Intent intent=getIntent();
        txtuser.setText("welcome");

        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentManager manager=getSupportFragmentManager();
//                switch(item.getItemId()){
//                    case R.id.id_phieumuon:
//                        setTitle("Quản Lí Phiếu Mượn");
//                        break;
//                }
                int id=item.getItemId();
                if(id==R.id.id_phieumuon){
                    setTitle("Quản Lý Phiếu Mượn");
                }else if(id==R.id.id_loaisach){
                    setTitle("Quản Lý Loại Sách");
                }else if(id==R.id.id_sach){
                    setTitle("Quản Lý Sách");
                }else if(id==R.id.id_thanhvien){
                    setTitle("Quản Lý Thành Viên");
                }else if(id==R.id.id_top){
                    setTitle("Top 10");
                }else if(id==R.id.id_thongke){
                    setTitle("Thống kê");
                }else if(id==R.id.id_adduser){
                    setTitle("Thêm Thành Viên");
                }else if(id==R.id.id_pass){
                    setTitle("Đổi Mật Khẩu");
                }else if(id==R.id.id_exit){
                    setTitle("Đăng Xuất");
                }
                drawerLayout.closeDrawers();
                return false;
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        if(id==android.R.id.home)
            drawerLayout.openDrawer(GravityCompat.START);
        return super.onOptionsItemSelected(item);
    }

}