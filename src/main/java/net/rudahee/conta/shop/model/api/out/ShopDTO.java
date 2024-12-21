package net.rudahee.conta.shop.model.api.out;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ShopDTO {
    private UUID id;
    private String name;
}
