package com.iris.restapi.retrofit.vo;

/**
 * Created by Adel on 2016. 9. 5..
 */
public class MainResultVO {
    private Banner banner;

    public Banner getBanner() {
        return banner;
    }

    public class Banner{
        private MainBannerVO banner;
        private MainCelebVO celeb;
        private MainItemVO try_me;
        private MainItemVO comming_soon;

        public MainBannerVO getBanner() {
            return banner;
        }

        public MainCelebVO getCeleb() {
            return celeb;
        }

        public MainItemVO getTry_me() {
            return try_me;
        }

        public MainItemVO getComming_soon() {
            return comming_soon;
        }
    }

}
