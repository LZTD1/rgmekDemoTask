package rgmek.backend.api.service;

import rgmek.backend.dto.database.AdressAll;

public interface AddressAllService {
    public AdressAll getAddressByFias(String fias);
}
