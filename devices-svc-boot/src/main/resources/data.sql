INSERT
INTO SIMS (id, operatorCode, countryName, status)
VALUES (99999, 'vodafone', 'Spain', 'WAITING_FOR_ACTIVATION'),
       (99998, 'vodafone', 'Spain', 'DEACTIVATED'),
       (99997, 'vodafone', 'Spain', 'BLOCKED'),
       (99996, 'vodafone', 'Spain', 'ACTIVE'),
       (99995, 'vodafone', 'Spain', 'ACTIVE'),
       (99994, 'vodafone', 'Spain', 'ACTIVE'),
       (99993, 'vodafone', 'Spain', 'WAITING_FOR_ACTIVATION'),
       (99992, 'vodafone', 'Italy', 'WAITING_FOR_ACTIVATION'),
       (99991, 'vodafone', 'Italy', 'WAITING_FOR_ACTIVATION'),
       (99990, 'vodafone', 'Italy', 'WAITING_FOR_ACTIVATION'),
       (99989, 'vodafone', 'Italy', 'WAITING_FOR_ACTIVATION'),
       (99988, 'vodafone', 'Italy', 'WAITING_FOR_ACTIVATION');

INSERT
INTO DEVICES (id, status, temperature, sim)
VALUES (99999, 'NOT_READY', 10.0, 99999),
       (99998, 'READY', 10.0, 99998),
       (99997, 'READY', 25.0, 99997),
       (99996, 'READY', 80.0, 99996),
       (99995, 'READY', 25.0, 99995),
       (99994, 'READY', 45.0, 99994),
       (99993, 'NOT_READY', 55.0, 99993),
       (99992, 'NOT_READY', 60.0, 99992),
       (99991, 'NOT_READY', 100.0, 99991),
       (99990, 'NOT_READY', -26.0, 99990),
       (99989, 'NOT_READY', 90.0, 99989),
       (99988, 'NOT_READY', 10.0, 99988);