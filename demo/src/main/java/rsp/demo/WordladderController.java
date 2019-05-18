package rsp.demo;

import rsp.demo.Wordladder;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class WordladderController {

    @RequestMapping("/ladder")
    public String ladder(@RequestParam(value = "b") String begin,
                          @RequestParam(value = "e") String end) {
        Wordladder wl = new Wordladder();
        return wl.ss(begin,end);
    }
}