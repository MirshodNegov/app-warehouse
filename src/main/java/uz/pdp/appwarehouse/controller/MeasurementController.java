package uz.pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.Measurement;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.service.MeasurementService;

@RestController
@RequestMapping("/measurement")
public class MeasurementController {

    @Autowired
    MeasurementService measurementService;

    @PostMapping
    public Result add(@RequestBody Measurement measurement){
        Result result = measurementService.addMeasurement(measurement);
        return result;
    }

    // get list, get one , edit , delete

    @GetMapping
    public Page<Measurement> get(@RequestParam int page){
        Page<Measurement> measurements = measurementService.get(page);
        return measurements;
    }

    @GetMapping("/{id}")
    public Measurement getOne(@PathVariable Integer id){
        Measurement measurement = measurementService.getOne(id);
        return measurement;
    }

    @PutMapping("/{id}")
    public Result edit(@PathVariable Integer id,@RequestBody Measurement measurement){
        Result result = measurementService.edit(id, measurement);
        return result;
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        Result result = measurementService.delete(id);
        return result;
    }

}
