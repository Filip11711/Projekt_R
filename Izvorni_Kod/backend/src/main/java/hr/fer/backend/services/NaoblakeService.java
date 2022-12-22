package hr.fer.backend.services;

import hr.fer.backend.repository.NaoblakeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
@AllArgsConstructor
public class NaoblakeService {
    private final NaoblakeRepository naoblakeRepository;

    public boolean downloadData(Date datum) {
        return true;
    }
}
