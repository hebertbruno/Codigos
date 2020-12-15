<?php

namespace common\models;

use Yii;

/**
 * This is the model class for table "jogada".
 *
 * @property integer $id
 * @property integer $id_user
 * @property string $auth_key
 * @property integer $pontuacao
 * @property string $data_hora
 */
class Jogada extends \yii\db\ActiveRecord
{
    /**
     * @inheritdoc
     */
    public static function tableName()
    {
        return 'jogada';
    }

    /**
     * @inheritdoc
     */
    public function rules()
    {
        return [
            [['id_user', 'auth_key', 'pontuacao'], 'required'],
            [['id_user', 'pontuacao'], 'integer'],
            [['data_hora'], 'safe'],
            [['auth_key'], 'string', 'max' => 32],
        ];
    }

    /**
     * @inheritdoc
     */
    public function attributeLabels()
    {
        return [
            'id' => 'ID',
            'id_user' => 'Id User',
            'auth_key' => 'Auth Key',
            'pontuacao' => 'Pontuacao',
            'data_hora' => 'Data Hora',
        ];
    }
}
