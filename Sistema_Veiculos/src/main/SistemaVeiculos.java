package main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate; // import para conseguir usar o LocalDate

import conexao.Conexao;

// classe com os atributos em comum com outras classes
	class CaracteristicasComuns {
		// Atributos comuns
		private String modelo;
		private int anofabricacao;
		private String montadora;
		private String cor;
		private int km;
			// metodo construtor para montar as caracteristicas
			public CaracteristicasComuns (String modelo, int anofabricacao, String montadora, String cor, int km) {
				setModelo(modelo);
		        setAnofabricacao(anofabricacao);
		        setMontadora(montadora);
		        setCor(cor);
		        setKm(km);
			}
			// método get para acessar o atributo
			public String getModelo() {
				return modelo;
			}
			// método set para modificar o valor do atributo
			public void setModelo(String modelo) {
				if (modelo == null || modelo.trim().isEmpty()) { // trim para tirar os espaços desnecessários e isEmpty para avisar se estiver vazio
		            throw new IllegalArgumentException("Informe o modelo do veículo");
			}
				this.modelo = modelo;
			}

			public int getAnofabricacao() {
				return anofabricacao;
			}

			public void setAnofabricacao(int anofabricacao) {
		        int anoMinimo = 1900; // int para fazer a validação do ano de fabricação
		        int anoMaximo = LocalDate.now().getYear() + 1; // limite do ano de fabricação (ano atual + 1)

		        if (anofabricacao < anoMinimo || anofabricacao > anoMaximo) { // se não estiver dentro do limite vai dar erro
		            throw new IllegalArgumentException("O ano de fabricação do veículo deve ser entre " + anoMinimo + " e " + anoMaximo);
		        }
		        this.anofabricacao = anofabricacao;
		    }

			public String getMontadora() {
				return montadora;
			}

			public void setMontadora(String montadora) {
				if (montadora == null || montadora.trim().isEmpty()) { 
		            throw new IllegalArgumentException("Informe a montadora do veículo");
		        }
		        this.montadora = montadora;
		    }

			public String getCor() {
				return cor;
			}

			 public void setCor(String cor) {
			        if (cor == null || cor.trim().isEmpty()) { 
			            throw new IllegalArgumentException("Informe a cor do veiculo");
			        }
			        this.cor = cor;
			 }

			public int getKm() {
				return km;
			}

			public void setKm(int km) {
		        if (km < 0) { // se for abaixo de 0 vai dar erro
		            throw new IllegalArgumentException("Informe corretamente a kilometragem do veiculo");
		        }
		        this.km = km;
		    }
			// metodo para retornar as informações formatadas
			public String descricao() {
				return "\n Modelo: " + modelo + " \n Ano de Fabricação: " + anofabricacao + " \n Montadora: " + montadora + " \n Cor: " + cor + " \n Kilometragem: " + km;
			}
	}
	// classe Automóvel com os atributos específicos necessários
	class Automóvel extends CaracteristicasComuns {// extends para fazer a Herança dos atributos em comum colocados na classe CaracteristicasComuns
		// Atributos Específicos
		private int qntdPassageiro;
		private String freio;
		private String airbag;
			// metodo construtor para montar as caracteristicas
			public Automóvel(String modelo, int anofabricacao, String montadora, String cor, int km, int qntdPassageiro, String freio, String airbag) {
				super(modelo, anofabricacao, montadora, cor, km); // pega as caracteristicas do CaracteristicasComuns
				 setQntdPassageiro(qntdPassageiro); // set para fazer o sistema de validação
			     setFreio(freio);
			     setAirbag(airbag);
			}
			// método get para acessar o atributo
			public int getQntdPassageiro() {
				return qntdPassageiro;
			}
			// método set para modificar o valor do atributo
			public void setQntdPassageiro(int qntdPassageiro) {
		        if (qntdPassageiro <= 0) { // se for igual ou abaixo de 0 vai dar erro
		            throw new IllegalArgumentException("A quantidade de passageiros do automóvel precisa ser maior que 0");
		        }
		        this.qntdPassageiro = qntdPassageiro;
		    }

			public String getFreio() {
				return freio;
			}

			public void setFreio(String freio) {
				if (freio == null || freio.trim().isEmpty()) { // trim para tirar os espaços desnecessários e isEmpty para avisar se estiver vazio
		            throw new IllegalArgumentException("Informe o tipo de freio do automóvel");
		        }
		        this.freio = freio;
		    }

			public String getAirbag() {
				return airbag;
			}

			public void setAirbag(String airbag) {
				if (airbag == null || airbag.trim().isEmpty()) { 
		            throw new IllegalArgumentException("Informe o airbag do automóvel");
		        }
		        this.airbag = airbag;
		    }
			
			// metodo para retornar as informações formatadas
			public String descricao() {
				// "super" para pegar as informaçoes formatadas da classe CaracteristicasComuns e adicionar as informações especificas dessa classe
				return super.descricao() + " \n Quantidade de Passageiros: " + qntdPassageiro + "\n Tipo de freio: " + freio + "\n Airbag: " + airbag;
			}
			public void salvarProduto(Connection conexao) {
			        String sql = "INSERT INTO Automovel (modelo, anofabricacao, montadora, cor, km, qntdPassageiro, freio, airbag) VALUES( ?, ?, ?, ?, ?, ?, ?, ?)";
			        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
			            stmt.setString(1, getModelo());
			            stmt.setInt(2, getAnofabricacao());
			            stmt.setString(3, getMontadora());
			            stmt.setString(4, getCor());
			            stmt.setInt(5, getKm());
			            stmt.setInt(6, getQntdPassageiro());
			            stmt.setString(7, getFreio());
			            stmt.setString(8, getAirbag());
			            
			            int rowsUpdated = stmt.executeUpdate();
			            if (rowsUpdated > 0) {
			                System.out.println("\nProduto inserido com sucesso!");
			            }
			        } catch (SQLException e) {
			            e.printStackTrace();
			            System.err.println("Erro ao inserir o produto: " + e.getMessage());
			        }
			    }
			}
	// classe Motocicleta com os atributos específicos necessários
	class Motocicleta extends CaracteristicasComuns { // extends para fazer a Herança dos atributos gerais colocados na  classe CaracteristicasComuns
		// Atributos específicos
		private int cilindrada;
		private double torque;
		// metodo construtor para montar as caracteristicas
		public Motocicleta(String modelo, int anofabricacao, String montadora, String cor, int km, int cilindrada, double torque) {
			super(modelo, anofabricacao, montadora, cor, km); // pega as caracteristicas do CaracteristicasComuns
			setCilindrada(cilindrada); // set para fazer o sistema de validação
	        setTorque(torque);
		}
		// método get para acessar o atributo
		public int getCilindrada() {
			return cilindrada;
		}
		// método set para modificar o valor do atributo
		public void setCilindrada(int cilindrada) {
	        if (cilindrada <= 0) { // se for igual ou abaixo de 0 vai dar erro
	            throw new IllegalArgumentException("Informe a quantidade de cilindradas da moto");
	        }
	        this.cilindrada = cilindrada;
	    }

		public double getTorque() {
			return torque;
		}

		public void setTorque(double torque) {
	        if (torque < 0) { // se for abaixo de 0 vai dar erro
	            throw new IllegalArgumentException("Informe o torque da moto");
	        }
	        this.torque = torque;
	    }
		// metodo para retornar as informações formatadas
		public String descricao() {
			// "super" para pegar as informaçoes formatadas da classe CaracteristicasComuns e adicionar as informações especificas dessa classe
			return super.descricao() + " \n Cilindradas: " + cilindrada + " \n Torque: " + torque + " kgfm";
		}
		public void salvarProduto(Connection conexao) {
			// método que insere as informações
		    String sql = "INSERT INTO Motocicleta (modelo, anofabricacao, montadora, cor, km, cilindrada, torque) VALUES( ?, ?, ?, ?, ?, ?, ?)";
		    try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
		        stmt.setString(1, getModelo()); // Define o modelo na consulta SQL
		        stmt.setInt(2, getAnofabricacao()); // Define o ano de fabricação na consulta SQL
		        stmt.setString(3, getMontadora()); // Define a montadora na consulta SQL
		        stmt.setString(4, getCor()); // Define a cor na consulta SQL
		        stmt.setInt(5, getKm()); // Define a kilometragem na consulta SQL
		        stmt.setInt(6, getCilindrada()); // Define a quantidade de cilindradas na consulta SQL
		        stmt.setDouble(7, getTorque());// Define a quantidade de torque na consulta SQL
		        
		        int rowsUpdated = stmt.executeUpdate();
		        if (rowsUpdated > 0) {
		            System.out.println("\nProduto inserido com sucesso!");
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		        System.err.println("Erro ao inserir o produto: " + e.getMessage());
		    }
		}

	}
	// classe Caminhões com os atributos específicos necessários
	class Caminhao extends CaracteristicasComuns { // extends para fazer a Herança dos atributos gerais colocados na  classe CaracteristicasComuns
		// Atributos específicos
		private int qntdEixos;
		private double pesoBruto;
		// metodo construtor para montar as caracteristicas
		public Caminhao(String modelo, int anofabricacao, String montadora, String cor, int km, int qntdEixos, double pesoBruto) {
			super(modelo, anofabricacao, montadora, cor, km); // pega as caracteristicas do CaracteristicasComuns
			setQntdEixos(qntdEixos); // set para fazer o sistema de validação
	        setPesoBruto(pesoBruto);
		}
		// método get para acessar o atributo
		public int getQntdEixos() {
			return qntdEixos;
		}
		// método set para modificar o valor do atributo
		public void setQntdEixos(int qntdEixos) {
	        if (qntdEixos <= 0) { // se for igual ou abaixo de zero vai dar erro
	            throw new IllegalArgumentException("Informe a quantidade de eixos do caminhão");
	        }
	        this.qntdEixos = qntdEixos;
	    }
		
		public double getPesoBruto() {
			return pesoBruto;
		}
		public void setPesoBruto(double pesoBruto) {
	        if (pesoBruto <= 0) { // se for igual ou abaixo de zero vai dar erro
	            throw new IllegalArgumentException("Informe o peso bruto do caminhão");
	        }
	        this.pesoBruto = pesoBruto;
	    }
		// metodo para retornar as informações formatadas
		public String descricao() {
			// "super" para pegar as informaçoes formatadas da classe CaracteristicasComuns e adicionar as informações especificas dessa classe
			return super.descricao() + " \n Quantidade de Eixos: " + qntdEixos + " \n Peso bruto: " + pesoBruto + " toneladas";
		}
		public void salvarProduto(Connection conexao) {
			// método que insere as informações
		    String sql = "INSERT INTO Caminhao (modelo, anofabricacao, montadora, cor, km, qntdEixos, pesoBruto) VALUES( ?, ?, ?, ?, ?, ?, ?)";
		    try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
		        stmt.setString(1, getModelo()); // Define o modelo na consulta SQL
		        stmt.setInt(2, getAnofabricacao()); // Define o ano de fabricação na consulta SQL
		        stmt.setString(3, getMontadora()); // Define a montadora na consulta SQL
		        stmt.setString(4, getCor()); // Define a cor na consulta SQL
		        stmt.setInt(5, getKm()); // Define a kilometragem na consulta SQL
		        stmt.setInt(6, getQntdEixos()); // Define a quantidade de Eixosna consulta SQL
		        stmt.setDouble(7, getPesoBruto());// Define a quantidade de Peso Bruto na consulta SQL
		        
		        int rowsUpdated = stmt.executeUpdate();
		        if (rowsUpdated > 0) {
		            System.out.println("\nProduto inserido com sucesso!");
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		        System.err.println("Erro ao inserir o produto: " + e.getMessage());
		    }
		}
	}
	// classe Bicicleta com os atributos específicos necessários
	class Bicicleta {
		// Atributos específicos
		private String modelo;
		private int anofabricacao;
		private String marca;
		private String cor;
		private String material;
		private int qntdMarcha;
		private String amortecedor;
		// metodo construtor para montar as caracteristicas
		public Bicicleta(String modelo, int anofabricacao, String marca, String cor, String material, int qntdMarcha, String amortecedor) {
			setModelo(modelo); // set para fazer o sistema de validação
	        setAnofabricacao(anofabricacao);
	        setMarca(marca);
	        setCor(cor);
	        setMaterial(material);
	        setQntdMarcha(qntdMarcha);
	        setAmortecedor(amortecedor);
		}
		// método get para acessar o atributo
		public String getModelo() {
			return modelo;
		}
		// método set para modificar o valor do atributo
		public void setModelo(String modelo) {
	        if (modelo == null || modelo.trim().isEmpty()) { // trim para tirar os espaços desnecessários e isEmpty para avisar se estiver vazio
	            throw new IllegalArgumentException("Informe o modelo da bicicleta");
	        }
	        this.modelo = modelo;
	    }

		public int getAnofabricacao() {
			return anofabricacao;
		}
		
		public void setAnofabricacao(int anofabricacao) {
	        int anoMinimo = 1900; // int para estabelecer o limite minimo do ano de fabricação
	        int anoMaximo = LocalDate.now().getYear() + 1; // limite maximo do ano de fabricação (ano atual + 1)

	        if (anofabricacao < anoMinimo || anofabricacao > anoMaximo) { // se estiver fora do limite vai dar erro
	            throw new IllegalArgumentException("O ano de fabricação da bicicleta deve ser entre " + anoMinimo + " e " + anoMaximo);
	        }
	        this.anofabricacao = anofabricacao;
	    }
		
		public String getMarca() {
			return marca;
		}

		public void setMarca(String marca) {
	        if (marca == null || marca.trim().isEmpty()) { 
	            throw new IllegalArgumentException("Informe a marca da bicicleta");
	        }
	        this.marca = marca;
	    }

		public String getCor() {
			return cor;
		}

		public void setCor(String cor) {
	        if (cor == null || cor.trim().isEmpty()) { 
	            throw new IllegalArgumentException("Informe a cor da bicicleta");
	        }
	        this.cor = cor;
	    }

		public String getMaterial() {
			return material;
		}

		 public void setMaterial(String material) {
		        if (material == null || material.trim().isEmpty()) { 
		            throw new IllegalArgumentException("Informe o material da bicicleta");
		        }
		        this.material = material;
		    }

		public int getQntdMarcha() {
			return qntdMarcha;
		}

		public void setQntdMarcha(int qntdMarcha) {
	        if (qntdMarcha <= 0) { // se for igual ou abaixo de 0 vai dar erro
	            throw new IllegalArgumentException("Informe a quantidade de marchas da bicicleta");
	        }
	        this.qntdMarcha = qntdMarcha;
	    }

		public String getAmortecedor() {
			return amortecedor;
		}

		public void setAmortecedor(String amortecedor) {
	        if (amortecedor == null || amortecedor.trim().isEmpty()) { 
	            throw new IllegalArgumentException("Informe o amortecedor da bicicleta");
	        }
	        this.amortecedor = amortecedor;
	    }
		
		// metodo para retornar as informações formatadas
		public String descricao() {
			return "\n Modelo da Bicicleta: " + modelo + " \n Ano de Fabricação: " + anofabricacao + " \n Marca: " + marca + " \n Cor: " + cor + " \n Material: " + material + " \n Quantidade de Marchas: " + qntdMarcha + " \n Amortecedor: " + amortecedor;
		}
		public void salvarProduto(Connection conexao) {
			// método que insere as informações
		    String sql = "INSERT INTO Bicicleta (modelo, anofabricacao, marca, cor, material, qntdMarcha, amortecedor) VALUES( ?, ?, ?, ?, ?, ?, ?)";
		    try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
		        stmt.setString(1, getModelo()); // Define o modelo na consulta SQL
		        stmt.setInt(2, getAnofabricacao()); // Define o ano de fabricação na consulta SQL
		        stmt.setString(3, getMarca()); // Define a marca na consulta SQL
		        stmt.setString(4, getCor()); // Define a cor na consulta SQL
		        stmt.setString(5, getMaterial()); // Define o material na consulta SQL
		        stmt.setInt(6, getQntdMarcha()); // Define a quantidade de marcha na consulta SQL
		        stmt.setString(7, getAmortecedor());// Define a quantidade de amortecedores na consulta SQL
		        
		        int rowsUpdated = stmt.executeUpdate();
		        if (rowsUpdated > 0) {
		            System.out.println("\nProduto inserido com sucesso!");
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		        System.err.println("Erro ao inserir o produto: " + e.getMessage());
		    }
		}
	}
	// classe Skate com os atributos específicos necessários
	class Skate {
		// Atributos específicos
		private String modelo;
		private int anofabricacao;
		private String marca;
		private String cor;
		private String tipoRoda;
		// metodo construtor para montar as caracteristicas
		public Skate(String modelo, int anofabricacao, String marca, String cor, String tipoRoda) {
			setModelo(modelo); // set para fazer o sistema de validação
			setAnofabricacao(anofabricacao);
	        setMarca(marca);
	        setCor(cor);
	        setTipoRoda(tipoRoda);
		}
		// método get para acessar o atributo
		public String getModelo() {
			return modelo;
		}
		// método set para modificar o valor do atributo
		public void setModelo(String modelo) {
	        if (modelo == null || modelo.trim().isEmpty()) { // trim para tirar os espaços desnecessários e isEmpty para avisar se estiver vazio
	            throw new IllegalArgumentException("Informe o modelo do skate");
	        }
	        this.modelo = modelo;
	    }

		public int getAnofabricacao() {
			return anofabricacao;
		}
		
		public void setAnofabricacao(int anofabricacao) {
	        int anoMinimo = 1900; // int para estabelecer o limite minimo do ano de fabricação
	        int anoMaximo = LocalDate.now().getYear() + 1; // limite maximo do ano de fabricação (ano atual + 1)

	        if (anofabricacao < anoMinimo || anofabricacao > anoMaximo) {
	            throw new IllegalArgumentException("O ano de fabricação do skate deve ser entre " + anoMinimo + " e " + anoMaximo);
	        }
	        this.anofabricacao = anofabricacao;
	    }
		
		public String getMarca() {
			return marca;
		}

		 public void setMarca(String marca) {
		        if (marca == null || marca.trim().isEmpty()) {
		            throw new IllegalArgumentException("Informe a marca do skate");
		        }
		        this.marca = marca;
		 }

		public String getCor() {
			return cor;
		}

		public void setCor(String cor) {
	        if (cor == null || cor.trim().isEmpty()) {
	            throw new IllegalArgumentException("Informe a cor do skate");
	        }
	        this.cor = cor;
	    }

		public String getTipoRoda() {
			return tipoRoda;
		}

		 public void setTipoRoda(String tipoRoda) {
		        if (tipoRoda == null || tipoRoda.trim().isEmpty()) {
		            throw new IllegalArgumentException("Informe o tipo de roda do skate");
		        }
		        this.tipoRoda = tipoRoda;
		 }
		 
		// metodo para retornar as informações formatadas
		public String descricao() {
			return " \n Modelo do Skate: " + modelo + " \nAno de fabricação: " + anofabricacao + " \n Marca: " + marca + " \n Cor: " + cor + " \n Tipo de Roda: " + tipoRoda;
		}
		public void salvarProduto(Connection conexao) {
			// método que insere as informações
		    String sql = "INSERT INTO Skate (modelo, anofabricacao, marca, cor, tipoRoda) VALUES( ?, ?, ?, ?, ?)";
		    try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
		        stmt.setString(1, getModelo()); // Define o modelo na consulta SQL
		        stmt.setInt(2, getAnofabricacao()); // Define o ano de fabricação na consulta SQL
		        stmt.setString(3, getMarca()); // Define a montadora na consulta SQL
		        stmt.setString(4, getCor()); // Define a cor na consulta SQL
		        stmt.setString(5, getTipoRoda()); // Define a kilometragem na consulta SQL

		        
		        int rowsUpdated = stmt.executeUpdate();
		        if (rowsUpdated > 0) {
		            System.out.println("\nProduto inserido com sucesso!");
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		        System.err.println("Erro ao inserir o produto: " + e.getMessage());
		    }
		}
	}
public class SistemaVeiculos {
	public static void main(String[] args) {
		Connection conexao = Conexao.conectar();
		if (conexao != null) {
		// criando novo objeto dando valor aos atributos
		 try { // faz a validação das informações do objeto
	            Automóvel a1 = new Automóvel("Corolla", 2024, "Toyota", "Preto", 12000, 5, "Freio a Disco", "Frontal");
	            Automóvel a2 = new Automóvel("RS6", 2023, "Audi", "Branca", 5000, 5, "Freio a Disco ventilados", "Frontais, laterais e cortina");
	            Automóvel a3 = new Automóvel("AMG G63", 2024, "Mercedes-Benz", "Cinza", 0, 5, "Freio a Disco ventilados, com ABS", "Frontais, laterais e cortina");
	            Motocicleta m1 = new Motocicleta("XRE 300", 2020, "Honda", "Branca", 13000, 291, 2.75);
	            Motocicleta m2 = new Motocicleta("Tiger 1200", 2023, "Triumph", "Azul", 0, 1160, 13.24);
	            Motocicleta m3 = new Motocicleta("S1000RR", 2023, "BMW", "Preta", 1000, 999, 11.52);
	            Caminhao c1 = new Caminhao("Scania 113", 1996, "Scania", "Vermelho", 112000, 3, 19);
	            Caminhao c2 = new Caminhao("Scania R 450", 2023, "Scania", "Preto", 43000, 3, 18);
	            Caminhao c3 = new Caminhao("Volvo FH 540", 2023, "Volvo", "Branco", 56000, 3, 18);
	            Bicicleta b1 = new Bicicleta("Caloi 10", 2022, "Caloi", "Azul", "Aço", 18, "Dianteiro");
	            Bicicleta b2 = new Bicicleta("Monark Elite", 2023, "Monark", "Vermelha", "Alumínio", 18, "Sem amortecedor");
	            Bicicleta b3 = new Bicicleta("Caloi Fusion", 2021, "Caloi", "Preto", "Alumínio", 21, "Dianteiro");
	            Skate s1 = new Skate("Element Skateboard", 2020, "Element", "Preto", "Uretano 54mm, 99A");
	            Skate s2 = new Skate("Cruizer Longboard", 2019, "Cruizer", "Madeira", "Uretano 70mm, 78A");
	            Skate s3 = new Skate("Fish Skateboard", 2020, "Fish", "Azul", "Uretano 60mm, 83A");
	            
	            // Imprimindo as informações formatadas de cada objeto
	            System.out.println(a1.descricao());
	            a1.salvarProduto(conexao);
	            System.out.println(a2.descricao());
	            a2.salvarProduto(conexao);
	            System.out.println(a3.descricao());
	            a3.salvarProduto(conexao);
	            System.out.println(m1.descricao());
	            m1.salvarProduto(conexao);
	            System.out.println(m2.descricao());
	            m2.salvarProduto(conexao);
	            System.out.println(m3.descricao());
	            m3.salvarProduto(conexao);
	            System.out.println(c1.descricao());
	            c1.salvarProduto(conexao);
	            System.out.println(c2.descricao());
	            c2.salvarProduto(conexao);
	            System.out.println(c3.descricao());
	            c3.salvarProduto(conexao);
	            System.out.println(b1.descricao());
	            b1.salvarProduto(conexao);
	            System.out.println(b2.descricao());
	            b2.salvarProduto(conexao);
	            System.out.println(b3.descricao());
	            b3.salvarProduto(conexao);
	            System.out.println(s1.descricao());
	            s1.salvarProduto(conexao);
	            System.out.println(s2.descricao());
	            s2.salvarProduto(conexao);
	            System.out.println(s3.descricao());
	            s3.salvarProduto(conexao);
	            
	     } 
		 catch (IllegalArgumentException erro) { // caso de erro em alguma validação aparece a mensagem de erro
	            System.err.println("Erro: " + erro.getMessage());
	          }
		}
	   }
}
