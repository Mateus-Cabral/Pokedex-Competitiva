package pokedex;

public class Pokemon {
	
	private String name;
	private String mainType;
	private String secType;
	private int dexNumber;
	
	private int level;
	
	private int hp;
	private int atk;
	private int def;
	private int spAtk;
	private int spDef;
	private int spd;
	
	private int iv;
	private int ev;
	
	
	public Pokemon() {
		
	}
	
	
	public Pokemon(String name, String mainType, String secType, int dexNumber) {
		this.name = name;
		this.mainType = mainType;
		this.secType = secType;
		this.dexNumber = dexNumber;
		
	}
	

	
	/**
	 * @param constructor for base level
	 */
	public Pokemon(String name, String mainType, String secType, int dexNumber, int hp, int atk, int def,
			int spAtk, int spDef, int spd) {
		
		this.name = name;
		this.mainType = mainType;
		this.secType = secType;
		this.dexNumber = dexNumber;
		this.level = 1;
		
		this.hp = hp;
		this.atk = atk;
		this.def = def;
		this.spAtk = spAtk;
		this.spDef = spDef;
		this.spd = spd;
	}
	
	
	/**
	 * @param Level, Status for calc (hp, atk, def, spatk, spdef, spd), 
	 * @param Nature, iv's and ev's for specific stat (min 1 | max 255 ev || min 0 | max 31 iv)
	 */
	public void calcStats(int level, String stat, String nature, int iv, int ev) {
		// Calculo de stats:
		// HP = floor(0.01 x (2 x BaseHp + IV + floor(0.25 x EV)) x Level) + Level + 10
		// Other Stats = (floor(0.01 x (2 x BaseStat + IV + floor(0.25 x EV)) x Level) + 5) x Nature
		// floor = função de arredondamento pra baixo)
		stat = stat.toLowerCase();
		nature = nature.toLowerCase();
		
		switch(stat)
		{
		case "hp":
			
			this.hp = (int) Math.floor((0.01 * (2* this.hp + iv + Math.floor(ev/4)) * level) + level + 10);
			break;
			
		case "atk":
			
			this.atk = (int) Math.floor(0.01 * (2 * this.atk + iv + Math.floor(ev/4)) * level) + 5;
			
			// Nature Checks
			// Raises the atk stat
			if(nature.equals("adamant")  || nature.equals("brave") || nature.equals("lonely") || nature.equals("naughty")) {
				this.atk = (int) Math.floor(this.atk + this.atk * 0.1);
			}
			
			// Lowers the atk stat
			if(nature.equals("bold") || nature.equals("modest") || nature.equals("timid") || nature.equals("calm")) {
				this.atk = (int) Math.floor(this.atk - this.atk * 0.1);
			}
			break;
			
		case "def":
			
			this.def = (int) Math.floor(0.01 * (2 * this.def + iv + Math.floor(ev/4)) * level) + 5;
			
			// Nature Checks
			// Raises the def stat
			
			if(nature.equals("bold")  || nature.equals("relaxed") || nature.equals("impish") || nature.equals("lax")) {
				this.def = (int) Math.floor(this.def + this.atk * 0.1);
			}
			
			// Lowers the def stat
			if(nature.equals("lonely") || nature.equals("mild") || nature.equals("hasty") || nature.equals("gentle")) {
				this.def = (int) Math.floor(this.def - this.def * 0.1);
			}
			break;
			
		case "spatk":
			
			this.spAtk = (int) Math.floor(0.01 * (2 * this.spAtk + iv + Math.floor(ev/4)) * level) + 5;
			
			
			// Nature Checks
			// Raises the Special Atk stat
			if(nature.equals("modest")  || nature.equals("mild") || nature.equals("quiet") || nature.equals("rash")) {
				this.spAtk = (int) Math.floor(this.spAtk + this.spAtk * 0.1);
			}
			
			// Lowers the Special Atk stat
			if(nature.equals("adamant") || nature.equals("impish") || nature.equals("careful") || nature.equals("jolly")) {
				this.spAtk = (int) Math.floor(this.spAtk - this.spAtk * 0.1);
			}
			
			break;
			
		case "spdef":
			
			this.spDef = (int) Math.floor(0.01 * (2 * this.spDef + iv + Math.floor(ev/4)) * level) + 5;
			
			// Nature Checks
			// Raises the Special Def stat
			if(nature.equals("calm")  || nature.equals("gentle") || nature.equals("sassy") || nature.equals("careful")) {
				this.spDef = (int) Math.floor(this.spDef + this.spDef * 0.1);
			}
			
			// Lowers the Special Def stat
			if(nature.equals("naughty") || nature.equals("lax") || nature.equals("rash") || nature.equals("naive")) {
				this.spDef = (int) Math.floor(this.spDef - this.spDef * 0.1);
			}
			
			break;
			
		case "spd":
			
			this.spd = (int) Math.floor(0.01 * (2* this.spd + iv + Math.floor(ev/4)) * level) + 5;
			
			// Nature Checks
			// Raises the Speed stat
			if(nature.equals("timid")  || nature.equals("hasty") || nature.equals("jolly") || nature.equals("naive")) {
				this.spd = (int) Math.floor(this.spd + this.spd * 0.1);
			}
			
			// Natures ++spd: Timid (-atk), Hasty (-def), Jolly (-atk), Naive (-sp.def)
			// brave relaxed quiet sassy
			
			// Lowers the Speed stat
			if(nature.equals("brave") || nature.equals("relaxed") || nature.equals("quiet") || nature.equals("sassy")) {
				this.spd = (int) Math.floor(this.spd - this.spd * 0.1);
			}
			
			break;
			
		}
		
		// EV = effort values
		// Pokemons são limitador a 255 pontos EV's por stat
		// Isso significa um total de 510 EV's
		// Status são recalculados após cada batalha dependendo dos EV's ganhos
		// Pra subir 1 ponto de stat, são necessários 4 EV's
		
		// Nature = aumentam um stat em 10% e diminuem um em 10%
		// Não afetam HP
		// Natures ++atk: Adamant (- sp.atk), Brave (-spd), Lonely (-def), Naughty (-sp.def)
		// Natures ++def: Bold (-atk), Relaxed (-spd), Impish (-sp.atk), Lax (-sp.def)
		// Natures ++sp.atk: Modest (-atk), Mild (-def), Quiet (-spd), Rash (-sp.def)
		// Natures ++sp.def: Calm (-atk), Gentle (-def), Sassy (-spd), Careful (-sp.atk)
		// Natures ++spd: Timid (-atk), Hasty (-def), Jolly (-atk), Naive (-sp.def)
		// Natures neutral: Hardy, Bashful, Docile, Quirky and Serious
		// IV = Individual values
		// IV tem um range de 0-31 por stat
	}
	
	//Getters and Setters
	
	
	
	/**
	 * @return the iv
	 */
	public int getIv() {
		return iv;
	}


	/**
	 * @param iv the iv to set
	 */
	public void setIv(int iv) {
		this.iv = iv;
	}


	/**
	 * @return the ev
	 */
	public int getEv() {
		return ev;
	}


	/**
	 * @param ev the ev to set
	 */
	public void setEv(int ev) {
		this.ev = ev;
	}


	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the Main Type
	 */
	public String getMainType() {
		return mainType;
	}
	/**
	 * @param mainType the mainType to set
	 */
	public void setMainType(String mainType) {
		this.mainType = mainType;
	}
	/**
	 * @return the Secondary Type of the Pokemon
	 */
	public String getSecType() {
		return secType;
	}
	/**
	 * @param secType the secType to set
	 */
	public void setSecType(String secType) {
		this.secType = secType;
	}
	/**
	 * @return the dexNumber
	 */
	public int getDexNumber() {
		return dexNumber;
	}
	/**
	 * @param dexNumber the dexNumber to set
	 */
	public void setDexNumber(int dexNumber) {
		this.dexNumber = dexNumber;
	}
	/**
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}
	/**
	 * @param level the level to set
	 */
	public void setLevel(int level) {
		this.level = level;
	}
	/**
	 * @return the hp
	 */
	public int getHp() {
		return hp;
	}
	/**
	 * @param hp the hp to set
	 */
	public void setHp(int hp) {
		this.hp = hp;
	}
	/**
	 * @return the attack
	 */
	public int getAtk() {
		return atk;
	}
	/**
	 * @param atk the atk to set
	 */
	public void setAtk(int atk) {
		this.atk = atk;
	}
	/**
	 * @return the defense
	 */
	public int getDef() {
		return def;
	}
	/**
	 * @param def the def to set
	 */
	public void setDef(int def) {
		this.def = def;
	}
	/**
	 * @return the Special Attack
	 */
	public int getSpAtk() {
		return spAtk;
	}
	/**
	 * @param spAtk the spAtk to set
	 */
	public void setSpAtk(int spAtk) {
		this.spAtk = spAtk;
	}
	/**
	 * @return the Special Defense
	 */
	public int getSpDef() {
		return spDef;
	}
	/**
	 * @param spDef the spDef to set
	 */
	public void setSpDef(int spDef) {
		this.spDef = spDef;
	}
	/**
	 * @return the Speed
	 */
	public int getSpd() {
		return spd;
	}
	/**
	 * @param spd the spd to set
	 */
	public void setSpd(int spd) {
		this.spd = spd;
	}
	
	
	
}
