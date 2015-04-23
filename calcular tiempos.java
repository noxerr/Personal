 StringBuffer sbuffer = new StringBuffer();
        inicio = System.currentTimeMillis();
        for (int i=0; i<1000000; i++) {
            sbuffer.append("zim");
        }
        fin = System.currentTimeMillis();
        System.out.println("Tiempo del StringBuffer: " + (fin-inicio));

        StringBuilder sbuilder = new StringBuilder();
        inicio = System.currentTimeMillis();
        for (int i=0; i<1000000; i++) {
            sbuilder.append("zim");
        }
        fin = System.currentTimeMillis();
        System.out.println("Tiempo del StringBuilder: " + (fin-inicio));