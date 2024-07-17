package rgmek.backend.api.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rgmek.backend.api.exceptions.NotFoundByFiasException;
import rgmek.backend.domain.JpaAddressAllRepository;
import rgmek.backend.dto.database.AdressAll;

@RequiredArgsConstructor
@Service
public class JpaAddressAllService implements AddressAllService {

    private final JpaAddressAllRepository repository;

    @Override
    @Transactional
    public AdressAll getAddressByFias(String fias) {
        return repository.findByFias(fias).orElseThrow(() -> new NotFoundByFiasException("Ничего не найдено!"));
    }
}
