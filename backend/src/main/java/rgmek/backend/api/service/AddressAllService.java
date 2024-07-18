package rgmek.backend.api.service;

import rgmek.backend.dto.database.AdressAll;

import java.util.List;

public interface AddressAllService {
    public List<AdressAll> getAddressByFias(String fias);
}
