package com.alimert.hotels.ui.splash;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.alimert.hotels.R;
import com.alimert.hotels.models.response.CityListData;
import com.alimert.hotels.ui.base.BaseActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends BaseActivity implements SplashView {

    @Inject
    SplashPresenter<SplashView> mPresenter;

    @BindView(R.id.rvCityList)
    RecyclerView rvCityList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        setUp();

        mPresenter.getCityListFromApi();
    }

    @Override
    public void setCityListToRecyclerView(List<CityListData> cityList) {
        SplashAdapter adapter = new SplashAdapter(getApplicationContext(), cityList,
                new SplashAdapter.OnItemClickListener() {
                    @Override
                    public void onClick(CityListData Item) {

                        Toast.makeText(getApplicationContext(), Item.getName(),
                                Toast.LENGTH_LONG).show();
                    }
                });

        rvCityList.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }

    @Override
    protected void setUp() {

        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(SplashActivity.this);

        rvCityList.setLayoutManager(new LinearLayoutManager(this));
    }
}