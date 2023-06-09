PGDMP         &                {            SearchChannel    15.2 (Debian 15.2-1.pgdg110+1)    15.2                0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false                       0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false                       0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false                       1262    16683    SearchChannel    DATABASE     z   CREATE DATABASE "SearchChannel" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'en_US.utf8';
    DROP DATABASE "SearchChannel";
                postgres    false            �            1259    16684    canali    TABLE     p   CREATE TABLE public.canali (
    id_canali integer NOT NULL,
    descrizione character varying(255) NOT NULL
);
    DROP TABLE public.canali;
       public         heap    postgres    false            �            1259    16689    canali_id_seq    SEQUENCE     �   CREATE SEQUENCE public.canali_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.canali_id_seq;
       public          postgres    false    214                       0    0    canali_id_seq    SEQUENCE OWNED BY     F   ALTER SEQUENCE public.canali_id_seq OWNED BY public.canali.id_canali;
          public          postgres    false    215            �            1259    16702    ricerche    TABLE     �   CREATE TABLE public.ricerche (
    id_ricerche integer NOT NULL,
    data_ricerca date NOT NULL,
    id_user integer NOT NULL
);
    DROP TABLE public.ricerche;
       public         heap    postgres    false            �            1259    33113    ricerche_canali    TABLE     j   CREATE TABLE public.ricerche_canali (
    id_ricerche integer NOT NULL,
    id_canali integer NOT NULL
);
 #   DROP TABLE public.ricerche_canali;
       public         heap    postgres    false            �            1259    16705    ricerche_id_ricerche_seq    SEQUENCE     �   CREATE SEQUENCE public.ricerche_id_ricerche_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 /   DROP SEQUENCE public.ricerche_id_ricerche_seq;
       public          postgres    false    218                        0    0    ricerche_id_ricerche_seq    SEQUENCE OWNED BY     U   ALTER SEQUENCE public.ricerche_id_ricerche_seq OWNED BY public.ricerche.id_ricerche;
          public          postgres    false    219            �            1259    16693    utenti    TABLE     �   CREATE TABLE public.utenti (
    id_user integer NOT NULL,
    nome character varying(255) NOT NULL,
    cognome character varying(255) NOT NULL,
    email character varying(255) NOT NULL
);
    DROP TABLE public.utenti;
       public         heap    postgres    false            �            1259    16698    user_id_seq    SEQUENCE     �   CREATE SEQUENCE public.user_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 "   DROP SEQUENCE public.user_id_seq;
       public          postgres    false    216            !           0    0    user_id_seq    SEQUENCE OWNED BY     B   ALTER SEQUENCE public.user_id_seq OWNED BY public.utenti.id_user;
          public          postgres    false    217            v           2604    16690    canali id_canali    DEFAULT     m   ALTER TABLE ONLY public.canali ALTER COLUMN id_canali SET DEFAULT nextval('public.canali_id_seq'::regclass);
 ?   ALTER TABLE public.canali ALTER COLUMN id_canali DROP DEFAULT;
       public          postgres    false    215    214            x           2604    16706    ricerche id_ricerche    DEFAULT     |   ALTER TABLE ONLY public.ricerche ALTER COLUMN id_ricerche SET DEFAULT nextval('public.ricerche_id_ricerche_seq'::regclass);
 C   ALTER TABLE public.ricerche ALTER COLUMN id_ricerche DROP DEFAULT;
       public          postgres    false    219    218            w           2604    16699    utenti id_user    DEFAULT     i   ALTER TABLE ONLY public.utenti ALTER COLUMN id_user SET DEFAULT nextval('public.user_id_seq'::regclass);
 =   ALTER TABLE public.utenti ALTER COLUMN id_user DROP DEFAULT;
       public          postgres    false    217    216                      0    16684    canali 
   TABLE DATA           8   COPY public.canali (id_canali, descrizione) FROM stdin;
    public          postgres    false    214   �!                 0    16702    ricerche 
   TABLE DATA           F   COPY public.ricerche (id_ricerche, data_ricerca, id_user) FROM stdin;
    public          postgres    false    218   "                 0    33113    ricerche_canali 
   TABLE DATA           A   COPY public.ricerche_canali (id_ricerche, id_canali) FROM stdin;
    public          postgres    false    220   H"                 0    16693    utenti 
   TABLE DATA           ?   COPY public.utenti (id_user, nome, cognome, email) FROM stdin;
    public          postgres    false    216   s"       "           0    0    canali_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.canali_id_seq', 36, true);
          public          postgres    false    215            #           0    0    ricerche_id_ricerche_seq    SEQUENCE SET     G   SELECT pg_catalog.setval('public.ricerche_id_ricerche_seq', 75, true);
          public          postgres    false    219            $           0    0    user_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.user_id_seq', 57, true);
          public          postgres    false    217            z           2606    16692    canali canali_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY public.canali
    ADD CONSTRAINT canali_pkey PRIMARY KEY (id_canali);
 <   ALTER TABLE ONLY public.canali DROP CONSTRAINT canali_pkey;
       public            postgres    false    214            �           2606    33117 $   ricerche_canali ricerche_canali_pkey 
   CONSTRAINT     v   ALTER TABLE ONLY public.ricerche_canali
    ADD CONSTRAINT ricerche_canali_pkey PRIMARY KEY (id_ricerche, id_canali);
 N   ALTER TABLE ONLY public.ricerche_canali DROP CONSTRAINT ricerche_canali_pkey;
       public            postgres    false    220    220            ~           2606    16708    ricerche ricerche_pkey 
   CONSTRAINT     ]   ALTER TABLE ONLY public.ricerche
    ADD CONSTRAINT ricerche_pkey PRIMARY KEY (id_ricerche);
 @   ALTER TABLE ONLY public.ricerche DROP CONSTRAINT ricerche_pkey;
       public            postgres    false    218            |           2606    16701    utenti user_pkey 
   CONSTRAINT     S   ALTER TABLE ONLY public.utenti
    ADD CONSTRAINT user_pkey PRIMARY KEY (id_user);
 :   ALTER TABLE ONLY public.utenti DROP CONSTRAINT user_pkey;
       public            postgres    false    216            �           2606    33118 +   ricerche_canali fk8g5grptyin358gis1b1e9j5tk    FK CONSTRAINT     �   ALTER TABLE ONLY public.ricerche_canali
    ADD CONSTRAINT fk8g5grptyin358gis1b1e9j5tk FOREIGN KEY (id_canali) REFERENCES public.canali(id_canali);
 U   ALTER TABLE ONLY public.ricerche_canali DROP CONSTRAINT fk8g5grptyin358gis1b1e9j5tk;
       public          postgres    false    220    3194    214            �           2606    33123 +   ricerche_canali fkewytttkkjmkwhenu57899u9it    FK CONSTRAINT     �   ALTER TABLE ONLY public.ricerche_canali
    ADD CONSTRAINT fkewytttkkjmkwhenu57899u9it FOREIGN KEY (id_ricerche) REFERENCES public.ricerche(id_ricerche);
 U   ALTER TABLE ONLY public.ricerche_canali DROP CONSTRAINT fkewytttkkjmkwhenu57899u9it;
       public          postgres    false    220    3198    218            �           2606    16709    ricerche id_user    FK CONSTRAINT        ALTER TABLE ONLY public.ricerche
    ADD CONSTRAINT id_user FOREIGN KEY (id_user) REFERENCES public.utenti(id_user) NOT VALID;
 :   ALTER TABLE ONLY public.ricerche DROP CONSTRAINT id_user;
       public          postgres    false    216    218    3196               2   x����,I��LT0�24�LN�K�IU0�24�,J�T0�243��b���� �
�         #   x�33�4202�50�50�4�27E����=... v)�            x�33�44�2��\� v� )�D         ;   x���L�)���+��LI,�L-��|.K����D����Hsr&r&q&s��qqq J�     