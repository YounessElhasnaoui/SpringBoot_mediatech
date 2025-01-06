// src/main/resources/static/js/api-metadata.js

const apiMetadata = {
    Client: {
        endpoints: [
            {
                name: "Get All Clients",
                method: "GET",
                path: "/clients",
                bodyRequired: false
            },
            {
                name: "Create Client",
                method: "POST",
                path: "/clients",
                bodyRequired: true,
                fields: ["nom", "prenom", "telephone"]
            },
            {
                name: "Find Client by ID",
                method: "GET",
                path: "/clients/id/{id}",
                bodyRequired: false,
                pathParams: ["id"]
            },
            {
                name: "Delete Client by ID",
                method: "DELETE",
                path: "/clients/id/{id}",
                bodyRequired: false,
                pathParams: ["id"]
            },
            {
                name: "Update Client by ID",
                method: "PUT",
                path: "/clients/id/{id}",
                bodyRequired: true,
                pathParams: ["id"],
                fields: ["nom", "prenom", "telephone"]
            }
        ]
    },
    Produit: {
        endpoints: [
            { name: "Get All Produits", method: "GET", path: "/produits", bodyRequired: false },
            { name: "Create Produit", method: "POST", path: "/produits", bodyRequired: true, fields: ["ref", "libelle", "prix", "quantit_stock"] },
            { name: "Get Produit by ID", method: "GET", path: "/produits/{id}", bodyRequired: false, pathParams: ["id"] },
            { name: "Delete Produit by ID", method: "DELETE", path: "/produits/{id}", bodyRequired: false, pathParams: ["id"] },
            { name: "Update Produit by ID", method: "PUT", path: "/produits/{id}", bodyRequired: true, pathParams: ["id"], fields: ["ref", "libelle", "prix", "quantit_stock"] },
            { name: "Get Produit by Ref", method: "GET", path: "/produits/ref/{ref}", bodyRequired: false, pathParams: ["ref"] }
        ]
    },
    Facture: {
        endpoints: [
            { name: "Get All Factures", method: "GET", path: "/factures", bodyRequired: false },
            { name: "Create Facture", method: "POST", path: "/factures", bodyRequired: true, fields: ["ref", "date", "clientId"] },
            { name: "Get Facture by ID", method: "GET", path: "/factures/{id}", bodyRequired: false, pathParams: ["id"] },
            { name: "Update Facture by ID", method: "PUT", path: "/factures/{id}", bodyRequired: true, pathParams: ["id"], fields: ["ref", "date", "clientId"] },
            { name: "Delete Facture by ID", method: "DELETE", path: "/factures/{id}", bodyRequired: false, pathParams: ["id"] },
            { name: "Get Facture by Ref", method: "GET", path: "/factures/ref/{ref}", bodyRequired: false, pathParams: ["ref"] }
        ]
    },
    LigneFacture: {
        endpoints: [
            { name: "Get All LigneFactures", method: "GET", path: "/ligne-factures", bodyRequired: false },
            { name: "Create LigneFacture", method: "POST", path: "/ligne-factures", bodyRequired: true, fields: ["factureId", "produitId", "quantite"] },
            {
                name: "Get LigneFacture by Composite Key",
                method: "GET",
                path: "/ligne-factures/{factureId}/{produitId}",
                bodyRequired: false,
                pathParams: ["factureId", "produitId"]
            },
            {
                name: "Update LigneFacture by Composite Key",
                method: "PUT",
                path: "/ligne-factures/{factureId}/{produitId}",
                bodyRequired: true,
                pathParams: ["factureId", "produitId"],
                fields: ["factureId", "produitId", "quantite"]
            },
            {
                name: "Delete LigneFacture by Composite Key",
                method: "DELETE",
                path: "/ligne-factures/{factureId}/{produitId}",
                bodyRequired: false,
                pathParams: ["factureId", "produitId"]
            }
        ]
    }
};
