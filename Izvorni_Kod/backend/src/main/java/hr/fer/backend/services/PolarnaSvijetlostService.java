package hr.fer.backend.services;

import hr.fer.backend.repository.PolarnaSvijetlostRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PolarnaSvijetlostService {
    private final PolarnaSvijetlostRepository polarnaSvijetlostRepository;
}
