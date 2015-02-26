/**
 * Player.java
 */

package wiz.project.jan.player;

import java.util.Observable;
import java.util.Observer;

import wiz.project.jan.Hand;
import wiz.project.jan.Wind;



/**
 * プレイヤー
 */
public abstract class Player extends Observable implements Cloneable, Observer {
    
    /**
     * コンストラクタ
     */
    public Player() {
    }
    
    /**
     * コンストラクタ
     * 
     * @param name プレイヤー名。
     * @param wind 風。
     */
    public Player(final String name, final Wind wind) {
        setName(name);
        setWind(wind);
    }
    
    /**
     * コピーコンストラクタ
     * 
     * @param source 複製元。
     */
    public Player(final Player source) {
        if (source != null) {
            _name = source._name;
            _wind = source._wind;
            _hand = source._hand.clone();
        }
    }
    
    
    
    /**
     * ディープコピーを取得
     * 
     * @return ディープコピー。
     */
    @Override
    public abstract Player clone();
    
    /**
     * 手牌を取得
     * 
     * @return 手牌。
     */
    public final Hand getHand() {
        return _hand.clone();
    }
    
    /**
     * プレイヤー名を取得
     * 
     * @return プレイヤー名。
     */
    public final String getName() {
        return _name;
    }
    
    /**
     * 風を取得
     * 
     * @return 風。
     */
    public final Wind getWind() {
        return _wind;
    }
    
    /**
     * 手牌を設定
     * 
     * @param hand 手牌。
     */
    public final void setHand(final Hand hand) {
        if (hand != null) {
            _hand = hand.clone();
        }
        else {
            _hand.clear();
        }
        setChanged();
    }
    
    /**
     * プレイヤー名を設定
     * 
     * @param name プレイヤー名。
     */
    public final void setName(final String name) {
        if (name != null) {
            _name = name;
        }
        else {
            _name = "";
        }
        setChanged();
    }
    
    /**
     * 風を設定
     * 
     * @param wind 風。
     */
    public final void setWind(final Wind wind) {
        if (wind != null) {
            _wind = wind;
        }
        else {
            _wind = Wind.TON;
        }
        setChanged();
    }
    
    
    
    /**
     * プレイヤー名
     */
    protected String _name = "";
    
    /**
     * 風
     */
    protected Wind _wind = Wind.TON;
    
    /**
     * 手牌
     */
    protected Hand _hand = new Hand();
    
}

