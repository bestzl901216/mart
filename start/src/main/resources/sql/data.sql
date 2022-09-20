INSERT INTO `account` (name, encoded_password, creator, updater, version)
VALUES ('ricardo_zhou', 'e4a9e067cea519d9b1c67ab31f6a7a8a8ac45987b9fa9cf6c890314963778368',
        'ricardo_zhou', 'ricardo_zhou', 1);

INSERT INTO `account_authority` (account_name, type, role, creator, updater)
VALUES ('ricardo_zhou', 'SYSTEM', 'SUPER_ADMIN', 'ricardo_zhou', 'ricardo_zhou');
INSERT INTO `account_authority` (account_name, type, role, creator, updater)
VALUES ('ricardo_zhou', 'SYSTEM', 'ADMIN', 'ricardo_zhou', 'ricardo_zhou');