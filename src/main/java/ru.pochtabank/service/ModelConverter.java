package ru.pochtabank.service;

import ru.pochtabank.model.BankRegistry;
import ru.pochtabank.model.ClientRegistry;

import java.util.ArrayList;
import java.util.List;

public class ModelConverter {
    public static List<BankRegistry> convertFromClientToBank(List<ClientRegistry> clientRegistries) {
        List<BankRegistry> result = new ArrayList<>(clientRegistries.size());
        clientRegistries.forEach(client -> result.add(new BankRegistry(client)));
        return result;
    }

}
