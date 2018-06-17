package com.kalomiris.input;

import com.kalomiris.model.Owner;
import com.kalomiris.service.OwnerServiceImpl;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputOwner {
    private static OwnerServiceImpl ownerServiceImpl = new OwnerServiceImpl();
    private static BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));

    public static Owner inputOnwer() throws IOException {
        Owner owner;
        System.out.println("Please enter your id: ");
        String ownerId = scanner.readLine();
        if (!ownerServiceImpl.isExistedOwner(ownerId)) {
            System.out.println("Please enter your first name: ");
            String firstOwnerName = scanner.readLine();
            System.out.println("Please enter your last name: ");
            String lastOwnerName = scanner.readLine();
            owner = ownerServiceImpl.createOwner(firstOwnerName, lastOwnerName, ownerId);
        } else {
            owner = ownerServiceImpl.retrieveOwner(ownerId);
        }
        return owner;

    }
}
