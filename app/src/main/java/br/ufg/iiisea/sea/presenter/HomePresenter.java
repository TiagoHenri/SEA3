package br.ufg.iiisea.sea.presenter;

import br.ufg.iiisea.sea.utils.ViewPagerAdapter;

/**
 * Created by tiago on 25/09/16.
 */
public interface HomePresenter extends EntrarCallback {

    void configuraTabs(ViewPagerAdapter adapter);
    void onDestroy();
}
