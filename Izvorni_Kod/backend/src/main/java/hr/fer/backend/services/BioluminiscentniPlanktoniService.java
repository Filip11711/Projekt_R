package hr.fer.backend.services;

import hr.fer.backend.repository.BioluminiscentniPlanktoniRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BioluminiscentniPlanktoniService {
    private final BioluminiscentniPlanktoniRepository bioluminiscentniPlanktoniRepository;
}
