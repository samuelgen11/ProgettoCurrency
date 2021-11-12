-- Table: public.padrone

-- DROP TABLE public.padrone;

Create table currency (
	id_currency int primary key,
	pair1 varchar(30),
	pair2 varchar(30),
	times bigint,
	lastvalue float(20)
	);