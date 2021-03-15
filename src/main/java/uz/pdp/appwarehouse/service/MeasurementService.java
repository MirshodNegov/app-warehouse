package uz.pdp.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.entity.Measurement;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.repository.MeasurementRepository;

import java.util.Optional;

@Service
public class MeasurementService {

    @Autowired
    MeasurementRepository measurementRepository;

    public Result addMeasurement(Measurement measurement){
        boolean exists = measurementRepository.existsByName(measurement.getName());
        if (exists)
            return new Result("Bunday o'lchov birligi mavjud !",false);
        measurementRepository.save(measurement);
        return new Result("O'lchov birligi qo'shildi !",true);
    }

    public Page<Measurement> get(int page){
        Pageable pageable= PageRequest.of(page,5);
        Page<Measurement> measurementPage = measurementRepository.findAll(pageable);
        return measurementPage;
    }

    public Measurement getOne(Integer id){
        Optional<Measurement> optional = measurementRepository.findById(id);
        if (!optional.isPresent())
            return null;
        Measurement measurement = optional.get();
        return measurement;
    }

    public Result edit(Integer id,Measurement measurement){
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(id);
        if (!optionalMeasurement.isPresent())
            return new Result("Measurement id wrong !",false);
        Measurement measurement1 = optionalMeasurement.get();
        measurement1.setName(measurement.getName());
        measurement1.setActive(measurement.isActive());
        measurementRepository.save(measurement1);
        return new Result("Edited !",true);
    }

    public Result delete(Integer id){
        boolean exists = measurementRepository.existsById(id);
        if (!exists)
            return new Result("Measurement not found !",false);
        measurementRepository.deleteById(id);
        return new Result("Deleted !",true);
    }
}
