package org.example.vestigo.controller;

import org.example.vestigo.common.Result;
import org.example.vestigo.entity.TraceEvent;
import org.example.vestigo.repository.TraceEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/trace")
public class TraceEventController {  // 前台
        @Autowired
    private TraceEventRepository traceEventRepository;

/*          测试
        @GetMapping("/text-save")
    public Result<TraceEvent> testSave(){


            TraceEvent event = new TraceEvent();
            event.setEventTime(LocalDateTime.now());
            event.setDevice("PC");
            event.setSource("SHELL");
            event.setTitle("测试终端命令");
            event.setContent("mvn spring-boot:run");
            event.setProject("Vestigo");


            TraceEvent savedEvent = traceEventRepository.save(event);

            return Result.success(savedEvent);
        }
        */

    @GetMapping("/search")
    public Result<List<TraceEvent>> search(@RequestParam String keyword) {

        List<TraceEvent> list = traceEventRepository.findByTitleContainingOrContentContaining(keyword, keyword);

        return Result.success(list);

    }


}
