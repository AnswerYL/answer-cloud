/**
 * @projectName answer-cloud
 * @package com.answer.common.utils
 * @className com.answer.common.utils.CustomBanner
 */
package com.answer.common.utils;

import com.nepxion.banner.BannerConstant;
import com.nepxion.banner.Description;
import com.nepxion.banner.DescriptionBanner;
import com.nepxion.banner.LogoBanner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * CustomBanner
 * @description 自定义Banner类
 * @author answer_wx
 * @date 2024/1/18 18:32
 * @version 1.0
 */
public class CustomBanner {

    public static void show(LogoBanner logoBanner, Description... descriptions) {
        String bannerShown = System.getProperty(BannerConstant.BANNER_SHOWN, "true");
        if (!Boolean.parseBoolean(bannerShown)) {
            return;
        }
        System.out.println();
        String bannerShownAnsiMode = System.getProperty(BannerConstant.BANNER_SHOWN_ANSI_MODE, "false");
        if (Boolean.parseBoolean(bannerShownAnsiMode)) {
            System.out.println(logoBanner.getBanner());
        } else {
            System.out.println(logoBanner.getPlainBanner());
        }

        List<Description> list = new ArrayList<>();
        Collections.addAll(list, descriptions);
        DescriptionBanner descriptionBanner = new DescriptionBanner();
        System.out.println(descriptionBanner.getBanner(list));
    }
}