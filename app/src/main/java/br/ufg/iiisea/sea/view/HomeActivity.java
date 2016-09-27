package br.ufg.iiisea.sea.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import br.ufg.iiisea.sea.R;
import br.ufg.iiisea.sea.bean.Noticia;
import br.ufg.iiisea.sea.presenter.EntrarPresenter;
import br.ufg.iiisea.sea.presenter.EntrarPresenterImpl;
import br.ufg.iiisea.sea.presenter.HomePresenter;
import br.ufg.iiisea.sea.presenter.HomePresenterImpl;
import br.ufg.iiisea.sea.utils.ViewPagerAdapter;

/**
 * Created by fellipe on 20/09/16.
 */
public class HomeActivity extends AppCompatActivity implements HomeView, NoticiaView {

    private ProgressDialog progress;

    private HomePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        LayoutInflater mInflater = LayoutInflater.from(this);

        View mCustomView = mInflater.inflate(R.layout.custom_action_bar, null);
        TextView tvAppName = (TextView) mCustomView.findViewById(R.id.tvCustomBarAppName);
        tvAppName.setText(getResources().getString(R.string.app_name));

        getSupportActionBar().setCustomView(mCustomView);
        getSupportActionBar().setDisplayShowCustomEnabled(true);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setCurrentItem(0);

        presenter = new HomePresenterImpl(this, getApplicationContext(), adapter);
        presenter.configuraTabs();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    /**
     * Habilita e mostra o content de progresso do android com a mensagem de estar carregando.
     */
    @Override
    public void showProgressLoading() {
        progress = ProgressDialog.show(HomeActivity.this, getResources().getString(R.string.carregando),
                getResources().getString(R.string.aguarde), true);
    }

    /**
     * Desabilita ou esconde o content de progresso do android.
     */
    @Override
    public void hideProgress() {
        progress.dismiss();
    }

    /**
     * Mostra uma mensagem como um Toast na Tela de acordo com o tipo da mensagem vinda pelo arquivo R
     *
     * @param msg
     */
    @Override
    public void showToastMessage(int msg) {
        showToastByString(getResources().getString(msg));
    }

    /**
     * Mostra uma mensagem como um Toast via uma string vinda do presenter.
     *
     * @param msg
     */
    @Override
    public void showToastByString(String msg) {
        Toast.makeText(getApplicationContext(),
                msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void addNoticia(Noticia newNoticia) {

    }

    @Override
    public void addNoticia(List<Noticia> lista) {

    }

    @Override
    public void removeNoticia(Noticia oldNoticia) {

    }

    @Override
    public void showNenhumaNoticiaMessage() {

    }
}
