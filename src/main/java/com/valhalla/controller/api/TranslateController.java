package com.valhalla.controller.api;

import com.valhalla.tool.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Valhalla
 * @description translate api
 * @date 2019/10/16
 **/
@RestController
@RequestMapping("/api")
public class TranslateController {

    private enum APIURL {
        BAIDU("baidu",
                "http://api.fanyi.baidu.com/api/trans/vip/translate?q={q}&from={from}&to={to}&appid={appid}&salt={salt}&sign={sign}",
                "*",
                "*");

        private String name;
        private String url;
        private String id;
        private String key;
        private Map<String, String> params;

        APIURL(String name,String url,String id,String key) {
            this.name = name;
            this.url = url;
            this.id = id;
            this.key = key;
        }

        public Map translateParams (String query, String from, String to) {
            switch (name) {
                case "baidu":
                    params = new HashMap<>();
                    params.put("q", query);
                    params.put("from", from);
                    params.put("to", to);
                    params.put("appid", id);
                    String salt = String.valueOf(System.currentTimeMillis());
                    params.put("salt", salt);
                    String src = id + query + salt + key;
                    params.put("sign", MD5.encrypt(src));
                    return params;
                default:
                    return null;
            }

        }
    }

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/translate")
    public String translate(@RequestParam(required = true) String query, @RequestParam(defaultValue = "auto") String from, @RequestParam(defaultValue = "en") String to) {
        Map<String, String> requestBody = APIURL.BAIDU.translateParams(query, from, to);
        return restTemplate.getForObject(APIURL.BAIDU.url, String.class, requestBody).toString();
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }
}
