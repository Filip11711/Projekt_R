package hr.fer.backend.services;

import hr.fer.backend.repository.PozariRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PozariService {
    private final PozariRepository pozariRepository;
}
